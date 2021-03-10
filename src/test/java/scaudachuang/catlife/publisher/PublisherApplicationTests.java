package scaudachuang.catlife.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import scaudachuang.catlife.publisher.dao.DetectCatMapper;
import scaudachuang.catlife.publisher.entity.DetectCatTask;
import scaudachuang.catlife.publisher.service.DetectService;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
class PublisherApplicationTests {
    @Resource
    private DetectCatMapper DetectCatMapper;

    @Test
    void contextLoads() {
        System.out.println(DetectCatMapper.getResult("1"));
    }

}
