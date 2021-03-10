package scaudachuang.catlife.publisher.service.impl.provider.message;

import lombok.Data;

@Data
public class InsertMessage {
    private String tableName;
    private Object insertObject;
}
