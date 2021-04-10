package scaudachuang.catlife.publisher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "微信授权用户")
public class CatOwner {

    @ApiModelProperty(value = "用户id")
    private int ownerId;
    @ApiModelProperty(value = "微信后台授权信息", notes = "不能暴露到开发者服务器外")
    private String openId;
    @ApiModelProperty(value = "微信后台授权信息", notes = "不能暴露到开发者服务器外")
    private String sessionKey;
    @ApiModelProperty(value = "微信名")
    private String nickname;
    @ApiModelProperty(value = "用户头像")
    private String avatar;

    /*
    * 一对多级联
    *
    * */
    List<HaveCat> haveCatList;

    List<Memorandum> memorandumList;

    List<Moments> momentsList;

    List<Comments> commentsList;
}
