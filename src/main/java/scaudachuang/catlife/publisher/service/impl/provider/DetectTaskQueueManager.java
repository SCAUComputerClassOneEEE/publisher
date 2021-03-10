package scaudachuang.catlife.publisher.service.impl.provider;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import scaudachuang.catlife.publisher.config.RabbitMQConfig;
import scaudachuang.catlife.publisher.config.TaskGetConfig;
import scaudachuang.catlife.publisher.entity.DetectCatTask;
import scaudachuang.catlife.publisher.service.impl.provider.message.DeleteMessageBuilder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DetectTaskQueue（图片识别任务）的管理者
 * 1. 负责任务包装提交到队列
 * 2. 消息出错处理
 * 3. 查询识别结果计数器
 * @author best lu
 * @since 2021/3/9
 */
@Component
public class DetectTaskQueueManager implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    private final ConcurrentHashMap<String, Integer> taskMapCache = new ConcurrentHashMap<>();

    @Resource
    private TaskGetConfig config;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnsCallback(this);
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 消息确认
     * @param correlationData 消息内容
     * @param ack 是否确认
     * @param cause 失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            taskMapCache.put(correlationData.getId(), 0);
            System.out.println("确认了这条消息：" + correlationData.getId());
        }
        else {
            System.out.println("确认失败了：" + correlationData + "；出现异常：" + cause);
            if (Objects.requireNonNull(correlationData.getReturned()).getRoutingKey().equals(RabbitMQConfig.Detect_Cat_Task_Routing)) {
                taskMapCache.remove(correlationData.getId());
            }
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        String correlationId = returnedMessage.getMessage().getMessageProperties().getCorrelationId();
        System.out.println(returnedMessage + " send failed.");
        if (returnedMessage.getRoutingKey().equals(RabbitMQConfig.Detect_Cat_Task_Routing)) {
            taskMapCache.remove(correlationId);
        }
    }

    /**
     * 生产消息到消息队列
     * @param uuid 任务id
     * @param img 任务图片数据
     * @throws Exception cache容量已满
     */
    public void provideTask(String uuid, MultipartFile img) throws Exception {
        if (taskMapCache.size() > config.getMAX_TASK_CACHE())
            throw new Exception("Out of task map cache error.");
        Message message = MessageBuilder.withBody(img.getBytes()).build();
        CorrelationData correlationData = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.Cat_Life_Exchange,
                RabbitMQConfig.Detect_Cat_Task_Routing,
                message,
                correlationData);
    }

    /*
    * 从cache和数据中删除该任务记录
    * */
    public void removeByKeyFromCacheAndDB(String uuid) {
        taskMapCache.remove(uuid);
        Message message = DeleteMessageBuilder
                .onTable("detectcattask")
                .addKey(new DetectCatTask.P_K(uuid))
                .build();
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.Cat_Life_Exchange,
                RabbitMQConfig.Cat_Life_Delete_Routing,
                message
        );
    }

    public int retryGetTask(String uuid) {
        return this.retryGetTask(uuid, 1);
    }
    /*
    * cache中没有该uuid的记录，或者该记录超次数（识别超时），返回-1，重试失败
    * 否则返回当前重试次数，重试成功。
    * */
    public int retryGetTask(String uuid, int addTimes) {
        if (!taskMapCache.containsKey(uuid)) {
            return -1;
        }
        int newTime = taskMapCache.get(uuid) + addTimes;
        if (newTime > config.getMAX_RETRY_TIME()) {
            /*
            这里可能会出现：消息队列中的任务还没有被消费，
            但是重试次数已经超过了config.getMAX_RETRY_TIME()
            所以要启用removeByKeyFromCacheAndDB，删除map中的和将要在数据库中出现的。

            如何删除将要在数据库中出现的呢？利用消息队列，提交DELETE消息。
             */
            removeByKeyFromCacheAndDB(uuid);
            return -1;
        }
        taskMapCache.replace(uuid, newTime);
        return newTime;
    }
}