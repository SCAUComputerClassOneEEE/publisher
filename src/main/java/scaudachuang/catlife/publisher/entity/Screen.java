package scaudachuang.catlife.publisher.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Screen {
    private int nId;
    private int beNId;
    private Timestamp fDatetime;

    @Data
    public static class P_K implements PK{
        private int nId;
        private int beNId;

        public P_K(int nId, int beNId) {
            this.nId = nId;
            this.beNId = beNId;
        }
    }

    public P_K getPK() {
        return new P_K(nId, beNId);
    }
}
