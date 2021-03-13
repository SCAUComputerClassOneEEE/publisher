package scaudachuang.catlife.publisher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "猫咪")
public class Cat {
    @ApiModelProperty(value = "猫咪种类")
    private String catClass;
    @ApiModelProperty(value = "猫咪简介")
    private String introduction;
    @ApiModelProperty(value = "猫咪大头像（base64编码）")
    private String headPhoto;
}
