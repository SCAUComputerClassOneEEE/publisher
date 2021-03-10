package scaudachuang.catlife.publisher.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import scaudachuang.catlife.publisher.config.TaskGetConfig;
import scaudachuang.catlife.publisher.entity.Cat;
import scaudachuang.catlife.publisher.entity.DetectCatTask;
import scaudachuang.catlife.publisher.service.DetectService;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/cat")
public class DetectCatController {

    @Resource
    private TaskGetConfig config;

    @Resource
    private DetectService detectService;

    @RequestMapping(value = "/class",method = RequestMethod.POST)
    public String detectCat(@RequestParam("img") MultipartFile img) throws Exception {
        if (img.getSize() > config.getMAX_IMG_SIZE()){
            throw new Exception("img too large.");
        }

        String s = UUID.randomUUID().toString();
        DetectCatTask detectCatTask = new DetectCatTask(s);
        detectService.recordTask(detectCatTask, img);
        return s;
    }

    @RequestMapping(value = "/resultClass/{taskId}", method = RequestMethod.GET)
    public Cat getDetectTask(@PathVariable("taskId") String taskId) throws Exception {
        return detectService.getTaskResult(taskId);
    }
}
