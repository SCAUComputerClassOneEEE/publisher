package scaudachuang.catlife.publisher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(description = "识别任务")
public class DetectCatTask {
    @ApiModelProperty(value = "任务凭证id")
    private String taskId;
    @ApiModelProperty(value = "识别结果，猫咪种类", notes = "任务未完成时，为unknown")
    private String resultClass;
    @ApiModelProperty(value = "识别完成时间")
    private Timestamp consumeTime;
    @ApiModelProperty(value = "任务是否完成")
    private boolean isDone;

    public DetectCatTask() {

    }

    public DetectCatTask(String id) {
        this.taskId = id;
    }
}
