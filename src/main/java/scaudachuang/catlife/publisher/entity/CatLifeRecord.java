package scaudachuang.catlife.publisher.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CatLifeRecord {

    private int ownerId;
    private String catClass;
    private int haveCatId;
    private Timestamp reDateTime;

    private String growth;
    private String diet;
    private String hygiene;

}
