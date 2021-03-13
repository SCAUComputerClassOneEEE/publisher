package scaudachuang.catlife.publisher.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comments {
    private Timestamp timeOfCom;
    private Timestamp timeOfMom;
    private int ownerId;
    private String contentJSON;

}
