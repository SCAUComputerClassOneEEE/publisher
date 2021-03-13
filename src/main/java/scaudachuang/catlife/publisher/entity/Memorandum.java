package scaudachuang.catlife.publisher.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Memorandum {
    private int ownerId;
    private Timestamp reDatetime;
    private String taskJSON;
}
