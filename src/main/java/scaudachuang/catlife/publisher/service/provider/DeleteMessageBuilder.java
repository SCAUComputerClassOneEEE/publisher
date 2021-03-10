package scaudachuang.catlife.publisher.service.provider;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class DeleteMessageBuilder {
    private final DeleteMessage deleteMessage;

    private DeleteMessageBuilder(DeleteMessage deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

    public static DeleteMessageBuilder onTable(String tableName) {
        return new DeleteMessageBuilder(new DeleteMessage(tableName, new HashMap<>()));
    }

    public DeleteMessageBuilder addKey(String key, Object data) {
        this.deleteMessage.getPrimaryKeyMap().put(key, data);
        return this;
    }

    public Message build() {
        return MessageBuilder
                .withBody(JSON.toJSONString(deleteMessage).getBytes(StandardCharsets.UTF_8))
                .build();
    }
}
