package scaudachuang.catlife.publisher.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import scaudachuang.catlife.publisher.config.TaskGetConfig;
import scaudachuang.catlife.publisher.entity.Cat;
import scaudachuang.catlife.publisher.entity.DetectCatTask;
import scaudachuang.catlife.publisher.service.DetectorService;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 标注后的DELETE、POST、GET代表访问资源方式
 * /cats/classes   POST 识别任务创建
 * /cats/classes   GET 获取异步任务结果
 * 识别任务控制器，提供创建任务和查询任务的 api
 */
@RestController
@RequestMapping("/cat")
public class DetectCatController {

    @Resource
    private TaskGetConfig config;

    @Resource
    private DetectorService detectorService;

    @RequestMapping(value = "/classes",method = RequestMethod.POST)
    public String detectCat(@RequestParam("img") MultipartFile img) throws Exception {
        if (img.getSize() > config.getMAX_IMG_SIZE()){
            throw new Exception("img too large.");
        }

        String s = UUID.randomUUID().toString();
        DetectCatTask detectCatTask = new DetectCatTask(s);
        detectorService.recordTask(detectCatTask, img);
        return s;
    }

    @RequestMapping(value = "/classes/{taskId}", method = RequestMethod.GET)
    public Cat getDetectTask(@PathVariable("taskId") String taskId) throws Exception {
        return detectorService.getTaskResult(taskId);
    }
}
