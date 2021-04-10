package scaudachuang.catlife.publisher.service.impl.provider;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import scaudachuang.catlife.publisher.config.RabbitMQConfig;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Component
public class CatLifeQueueProvider {

    @Data
    public static class QueueCUDMessage {
        private Class<?> beanCl;
        private CUDType type;
        private Object element;

        public QueueCUDMessage(Class<?> beanCl, CUDType type, Object element) {
            this.beanCl = beanCl;
            this.type = type;
            this.element = element;
        }

        public enum CUDType {
            C_TYPE, U_TYPE, D_TYPE
        }
    }

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void provideDeleteMessage(Object opO) {
        provideMessage(opO, QueueCUDMessage.CUDType.D_TYPE);
    }

    public void provideUpdateMessage(Object opO) {
        provideMessage(opO, QueueCUDMessage.CUDType.U_TYPE);
    }

    public void provideInsertMessage(Object opO) {
        provideMessage(opO, QueueCUDMessage.CUDType.C_TYPE);
    }

    private void provideMessage(Object opO, QueueCUDMessage.CUDType opT) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.Cat_Life_Exchange,
                RabbitMQConfig.Cat_Life_Record_Routing,
                MessageBuilder.withBody(
                        JSON.toJSONString(new QueueCUDMessage(opO.getClass(), opT, opO))
                                .getBytes(StandardCharsets.UTF_8))
                        .build()
        );
    }
}
