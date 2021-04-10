package scaudachuang.catlife.publisher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@ApiModel(description = "猫圈动态")
public class Moments {
    @ApiModelProperty(value = "发布时间")
    private Timestamp timeOfMom;
    @ApiModelProperty(value = "用户id")
    private int ownerId;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "点赞数")
    private int likes;
    @ApiModelProperty(value = "发布图片")
    private String carryPhoto;
    @ApiModelProperty(value = "评论数")
    private int comTime;

    List<Comments> commentsList;
}
