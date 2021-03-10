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

    @Data
    public static class P_K implements PK{
        private int ownerId;
        private String catClass;
        private int haveCatId;

        public P_K(int ownerId, String catClass, int haveCatId) {
            this.ownerId = ownerId;
            this.catClass = catClass;
            this.haveCatId = haveCatId;
        }
    }

    public P_K getPK() {
        return new P_K(ownerId, catClass, haveCatId);
    }

}
