package scaudachuang.catlife.publisher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@ApiModel(description = "用户拥有的猫")
public class HaveCat {
    @ApiModelProperty(value = "用户id", notes = "主键1")
    private int ownerId;
    @ApiModelProperty(value = "猫咪种类", notes = "主键2")
    private String catClass;
    @ApiModelProperty(value = "该用户相同猫的第n个宠物", notes = "主键3")
    private int haveCatId;
    @ApiModelProperty(value = "生日")
    private Timestamp birthday;
    @ApiModelProperty(value = "宠物名字")
    private String catName;
    @ApiModelProperty(value = "是否绝育")
    private boolean isSterilization;
    @ApiModelProperty(value = "是否已经生育")
    private boolean isBear;
    @ApiModelProperty(value = "药物过敏", notes = "复合多值属性需要格式保存")
    private String drugAllergy;
    @ApiModelProperty(value = "喜爱", notes = "复合多值属性需要格式保存")
    private String likes;

    /*
    * 一对多级联
    * */
    List<CatLifeRecord> catLifeRecordList;
}
