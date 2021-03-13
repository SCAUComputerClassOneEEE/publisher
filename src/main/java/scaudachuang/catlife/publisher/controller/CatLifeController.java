package scaudachuang.catlife.publisher.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scaudachuang.catlife.publisher.entity.CatLifeRecord;
import scaudachuang.catlife.publisher.service.ReaderService;

import javax.annotation.Resource;

/**
 * 标注后的DELETE、POST、GET代表访问资源方式
 * 每日记录/cat/diary     POST,GET
 * 猫档案/cat/archives    POST,GET
 * @author best lu
 * @since 2021/3/10
 */
@Api(tags = "猫咪日常")
@RestController
@RequestMapping("/catLife")
public class CatLifeController {
    @Resource
    private ReaderService readerService;

    @RequestMapping(value = "/diary/{id}", method = RequestMethod.GET)
    public CatLifeRecord getCatLifeRecord(@PathVariable("id") String id) {
        System.out.println(id);
        return null;
    }
}
