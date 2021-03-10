package scaudachuang.catlife.publisher.entity;

import lombok.Data;

@Data
public class Cat {
    private String catClass;
    private String introduction;
    private String headPhoto;

    @Data
    public static class P_K implements PK{
        private String catClass;
        private String introduction;
        private String headPhoto;

        public P_K(String catClass, String introduction, String headPhoto) {
            this.catClass = catClass;
            this.introduction = introduction;
            this.headPhoto = headPhoto;
        }
    }

    public P_K getPK() {
        return new P_K(catClass, introduction, headPhoto);
    }
}
