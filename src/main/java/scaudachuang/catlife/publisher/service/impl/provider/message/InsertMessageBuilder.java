package scaudachuang.catlife.publisher.service.impl.provider.message;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;

import java.nio.charset.StandardCharsets;

public class InsertMessageBuilder {
    private final InsertMessage insertMessage;

    private InsertMessageBuilder(InsertMessage insertMessage) {
        this.insertMessage = insertMessage;
    }

    public static InsertMessageBuilder onTable(String tableName) {
        InsertMessage insertMessage = new InsertMessage();
        insertMessage.setTableName(tableName);
        return new InsertMessageBuilder(insertMessage);
    }

    public InsertMessageBuilder withObject(Object o) {
        this.insertMessage.setInsertObject(o);
        return this;
    }

    public Message build() {
        return MessageBuilder
                .withBody(JSON.toJSONString(insertMessage).getBytes(StandardCharsets.UTF_8))
                .build();
    }
}
