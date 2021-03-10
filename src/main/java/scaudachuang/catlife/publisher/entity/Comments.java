package scaudachuang.catlife.publisher.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comments {
    private Timestamp timeOfCom;
    private Timestamp timeOfMom;
    private int ownerId;
    private String contentJSON;

    @Data
    public static class P_K implements PK{
        private Timestamp timeOfCom;
        private Timestamp timeOfMom;
        private int ownerId;

        public P_K(Timestamp timeOfCom, Timestamp timeOfMom, int ownerId) {
            this.timeOfCom = timeOfCom;
            this.timeOfMom = timeOfMom;
            this.ownerId = ownerId;
        }
    }

    public P_K getPK() {
        return new P_K(timeOfCom, timeOfMom, ownerId);
    }
}
