package scaudachuang.catlife.publisher.service.provider;

import lombok.Data;

import java.util.Map;

@Data
public class DeleteMessage {
    private String tableName;
    private Map<String, Object> primaryKeyMap;

    public DeleteMessage(String tableName, Map<String, Object> primaryKeyMap) {
        this.tableName = tableName;
        this.primaryKeyMap = primaryKeyMap;
    }
}
