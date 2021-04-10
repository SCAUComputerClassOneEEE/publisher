package scaudachuang.catlife.publisher.service.impl.provider;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import scaudachuang.catlife.publisher.config.RabbitMQConfig;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * DetectTaskQueue（图片识别任务）的管理者
 * 1. 负责任务包装提交到队列
 * 2. 消息出错处理
 * 3. 查询识别结果计数器
 * @author best lu
 * @since 2021/3/9
 */
@Component
public class DetectTaskQueueManager {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 生产消息到消息队列
     * @param img 任务图片数据
     * @throws Exception cache容量已满
     */
    public String provideTask(MultipartFile img) throws Exception {
        Message message = MessageBuilder.withBody(img.getBytes()).build();
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.Cat_Life_Exchange,
                RabbitMQConfig.Detect_Cat_Task_Routing,
                message,
                correlationData);
        return uuid;
    }
}
