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

    @Data
    public static class P_K implements PK{
        private int ownerId;
        private String catClass;
        private int haveCatId;
        private Timestamp reDateTime;

        public P_K(int ownerId, String catClass, int haveCatId, Timestamp reDateTime) {
            this.ownerId = ownerId;
            this.catClass = catClass;
            this.haveCatId = haveCatId;
            this.reDateTime = reDateTime;
        }
    }

    public P_K getPK() {
        return new P_K(ownerId, catClass, haveCatId, reDateTime);
    }
}
