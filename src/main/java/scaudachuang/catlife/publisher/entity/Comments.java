package scaudachuang.catlife.publisher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(description = "动态下的评论")
public class Comments {
    @ApiModelProperty(value = "评论时间")
    private Timestamp timeOfCom;
    @ApiModelProperty(value = "被评论的动态的时间")
    private Timestamp timeOfMom;
    @ApiModelProperty(value = "被评论的动态的发布人")
    private int ownerId;
    @ApiModelProperty(value = "评论格式内容", notes = "格式：{评论者名字/被评论者名字/内容}，String中出现分隔符时，用//转义")
    private String contentJSON;
}
