package scaudachuang.catlife.publisher.service.impl.provider.message;

import lombok.Data;
import scaudachuang.catlife.publisher.entity.PK;


@Data
public class DeleteMessage {
    private String tableName;
    private PK pk;

    public DeleteMessage(String tableName, PK pk) {
        this.tableName = tableName;
        this.pk = pk;
    }
}
