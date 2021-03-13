package scaudachuang.catlife.publisher.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class HaveCat {
    private int ownerId;
    private String catClass;
    private int haveCatId;
    private Timestamp birthday;
    private String catName;
    private boolean isSterilization;
    private boolean isBear;
    private String drugAllergy;
    private String likes;
}
