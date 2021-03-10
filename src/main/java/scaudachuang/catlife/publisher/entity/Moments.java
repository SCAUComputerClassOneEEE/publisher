package scaudachuang.catlife.publisher.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Moments {
    private Timestamp timeOfMom;
    private int ownerId;
    private String content;
    private int likes;
    private String carryPhoto;
    private int comTime;

    @Data
    public static class P_K implements PK{
        private Timestamp timeOfMom;
        private int ownerId;

        public P_K(Timestamp timeOfMom, int ownerId) {
            this.timeOfMom = timeOfMom;
            this.ownerId = ownerId;
        }
    }

    public P_K getPK() {
        return new P_K(timeOfMom, ownerId);
    }
}
