package scaudachuang.catlife.publisher.service.impl.provider;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import scaudachuang.catlife.publisher.config.RabbitMQConfig;
import scaudachuang.catlife.publisher.entity.PK;
import scaudachuang.catlife.publisher.service.impl.provider.message.DeleteMessageBuilder;
import scaudachuang.catlife.publisher.service.impl.provider.message.InsertMessageBuilder;

import javax.annotation.Resource;

@Component
public class CatLifeQueueProvider {

    @Resource
    private RabbitTemplate rabbitTemplate;


    public void provideDeleteMessage(String tableName, PK pk) {
        Message build = DeleteMessageBuilder
                .onTable(tableName)
                .addKey(pk)
                .build();
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.Cat_Life_Exchange,
                RabbitMQConfig.Cat_Life_Delete_Routing,
                build
        );
    }

    public void provideInsertMessage(String tableName, Object beInsertOne) {
        Message build = InsertMessageBuilder
                .onTable(tableName)
                .withObject(beInsertOne)
                .build();
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.Cat_Life_Exchange,
                RabbitMQConfig.Cat_Life_Record_Routing,
                build
        );
    }

}
