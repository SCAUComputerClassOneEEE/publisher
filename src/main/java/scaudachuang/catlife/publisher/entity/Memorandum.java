package scaudachuang.catlife.publisher.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Memorandum {
    private int ownerId;
    private Timestamp reDatetime;
    private String taskJSON;

    @Data
    public static class P_K implements PK{
        private int ownerId;
        private Timestamp reDatetime;

        public P_K(int ownerId, Timestamp reDatetime) {
            this.ownerId = ownerId;
            this.reDatetime = reDatetime;
        }
    }

    public P_K getPK() {
        return new P_K(ownerId, reDatetime);
    }
}
