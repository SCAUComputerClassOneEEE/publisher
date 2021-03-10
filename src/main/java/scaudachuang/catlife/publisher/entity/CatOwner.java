package scaudachuang.catlife.publisher.entity;

import lombok.Data;

@Data
public class CatOwner {

    private int ownerId;
    private String openId;
    private String sessionKey;
    private String nickname;
    private String avatar;

    @Data
    public static class P_K implements PK{
        private int ownerId;
        public P_K(int ownerId) {
            this.ownerId = ownerId;
        }
    }

    public P_K getPK() {
        return new P_K(ownerId);
    }
}
