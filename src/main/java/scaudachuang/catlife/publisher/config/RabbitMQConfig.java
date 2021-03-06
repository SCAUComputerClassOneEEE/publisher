package scaudachuang.catlife.publisher.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;

@Configuration
public class RabbitMQConfig  implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public static final String Detect_Cat_Task_Queue = "DetectCatTaskQueue";
    public static final String Detect_Cat_Task_Routing = "DetectCatTaskRouting";
    public static final String Cat_Life_Record_Queue = "CatLifeRecordQueue";
    public static final String Cat_Life_Record_Routing = "CatLifeRecordRouting";
    public static final String Cat_Life_Exchange = "CatLifeExchange";

    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnsCallback(this);
        rabbitTemplate.setConfirmCallback(this);
    }

    @Bean
    public Queue detectCatTaskQueue() { return new Queue(Detect_Cat_Task_Queue,true); }
    @Bean
    public Queue catLifeRecordQueue() {
        return new Queue(Cat_Life_Record_Queue, true);
    }

    @Bean
    public DirectExchange catLifeExchange() {
        return new DirectExchange(Cat_Life_Exchange);
    }

    @Bean
    public Binding detectCatTaskRouting() { return BindingBuilder.bind(detectCatTaskQueue()).to(catLifeExchange()).with(Detect_Cat_Task_Routing); }
    @Bean
    public Binding catLifeRecordRouting() { return BindingBuilder.bind(catLifeRecordQueue()).to(catLifeExchange()).with(Cat_Life_Record_Routing); }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
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
            System.out.println("确认了这条消息：" + correlationData.getId());
        }
        else {
            System.out.println("确认失败了：" + correlationData + "；出现异常：" + cause);
            if (Objects.requireNonNull(correlationData.getReturned()).getRoutingKey().equals(RabbitMQConfig.Detect_Cat_Task_Routing)) {

            }
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        String correlationId = returnedMessage.getMessage().getMessageProperties().getCorrelationId();
        System.out.println(returnedMessage + " send failed.");
    }

}
