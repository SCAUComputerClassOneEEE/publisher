package scaudachuang.catlife.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import scaudachuang.catlife.publisher.entity.Cat;
import scaudachuang.catlife.publisher.entity.CatLifeRecord;
import scaudachuang.catlife.publisher.service.WriterService;

import javax.annotation.Resource;

@SpringBootTest
class PublisherApplicationTests {

    @Resource
    private WriterService queueProviderService;

    @Test
    void contextLoads() {
        CatLifeRecord catLifeRecord = new CatLifeRecord();
        catLifeRecord.setCatClass("sss");
        Cat cat = new Cat();
        cat.setCatClass("qqq");
        queueProviderService.sub(catLifeRecord);
        queueProviderService.sub(cat);
    }

}
