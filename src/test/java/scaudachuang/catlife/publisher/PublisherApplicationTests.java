package scaudachuang.catlife.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import scaudachuang.catlife.publisher.entity.Cat;
import scaudachuang.catlife.publisher.entity.CatLifeRecord;
import scaudachuang.catlife.publisher.service.WriterService;

import javax.annotation.Resource;

@SpringBootTest
class PublisherApplicationTests {

    @Test
    void contextLoads() {
        CatLifeRecord catLifeRecord = new CatLifeRecord();
        Object o = (Object) catLifeRecord;
        System.out.println(o.getClass());
    }

}
