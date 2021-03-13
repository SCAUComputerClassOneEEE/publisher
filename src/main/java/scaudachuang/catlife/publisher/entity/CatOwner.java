package scaudachuang.catlife.publisher.entity;

import lombok.Data;

@Data
public class CatOwner {

    private int ownerId;
    private String openId;
    private String sessionKey;
    private String nickname;
    private String avatar;

}
