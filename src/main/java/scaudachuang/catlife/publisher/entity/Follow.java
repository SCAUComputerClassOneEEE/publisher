package scaudachuang.catlife.publisher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(description = "粉丝关系")
public class Follow {
    @ApiModelProperty(value = "关注者")
    private int nId;
    @ApiModelProperty(value = "被关注者")
    private int beNId;
    @ApiModelProperty(value = "关注时间")
    private Timestamp fDatetime;
}
