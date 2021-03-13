package scaudachuang.catlife.publisher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(description = "用户备忘录")
public class Memorandum {
    @ApiModelProperty(value = "用户id")
    private int ownerId;
    @ApiModelProperty(value = "备忘时间")
    private Timestamp reDatetime;
    @ApiModelProperty(value = "备忘信息", notes = "多值属性，格式：{任务（打疫苗、洗澡等等，用户自填）/任务时间（date）/任务对象（猫名字）}")
    private String taskJSON;
}
