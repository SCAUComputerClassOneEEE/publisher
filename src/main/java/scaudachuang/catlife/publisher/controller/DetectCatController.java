package scaudachuang.catlife.publisher.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import scaudachuang.catlife.publisher.config.TaskGetConfig;
import scaudachuang.catlife.publisher.entity.Cat;
import scaudachuang.catlife.publisher.service.DetectorService;

import javax.annotation.Resource;

/**
 *
 * 标注后的 DELETE、POST、GET代表访问资源方式
 * /cats/classes   POST 识别任务创建
 * /cats/classes   GET 获取异步任务结果
 * 识别任务控制器，提供创建任务和查询任务的 api
 */
@Api(tags = "猫咪识别")
@RestController
@RequestMapping("/cats")
public class DetectCatController {

    @Resource
    private TaskGetConfig config;

    @Resource
    private DetectorService detectorService;

    @ApiOperation(value = "上传图片，创建识别任务", notes = "大小不超过3MB")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image", value = "用户拍摄或选择的图片", required = true, paramType = "form", dataType = "MultipartFile")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功，返回任务凭证")
    })
    @RequestMapping(value = "/classes",method = RequestMethod.POST)
    public String detectCat(@RequestParam("image") MultipartFile img) throws Exception {
        if (img.getSize() > config.getMAX_IMG_SIZE()){
            throw new Exception("img too large.");
        }
        return detectorService.recordTask(img);
    }

    @ApiOperation(value = "根据先前识别任务的返回凭证（string），查询识别结果", notes = "必须先调用上一个接口获取有效的凭证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务凭证", required = true, paramType = "path", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功，返回Cat实体")
    })
    @RequestMapping(value = "/classes/{taskId}", method = RequestMethod.GET)
    public Cat getDetectTask(@PathVariable("taskId") String taskId) throws Exception {
        return detectorService.getTaskResult(taskId);
    }
}
