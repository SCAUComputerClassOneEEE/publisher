package scaudachuang.catlife.publisher.service.impl.provider.message;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import scaudachuang.catlife.publisher.entity.PK;

import java.nio.charset.StandardCharsets;

public class DeleteMessageBuilder {
    private final DeleteMessage deleteMessage;

    private DeleteMessageBuilder(DeleteMessage deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

    public static DeleteMessageBuilder onTable(String tableName) {
        return new DeleteMessageBuilder(new DeleteMessage(tableName, null));
    }

    public DeleteMessageBuilder addKey(PK pk) {
        this.deleteMessage.setPk(pk);
        return this;
    }

    public Message build() {
        return MessageBuilder
                .withBody(JSON.toJSONString(deleteMessage).getBytes(StandardCharsets.UTF_8))
                .build();
    }
}
