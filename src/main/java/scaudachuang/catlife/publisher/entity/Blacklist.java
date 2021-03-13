package scaudachuang.catlife.publisher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(description = "Blacklist 黑名单实体类")
public class Blacklist {

    @ApiModelProperty(value = "屏蔽者的id")
    private int nId;
    @ApiModelProperty(value = "被屏蔽者的id")
    private int beNId;
    @ApiModelProperty(value = "屏蔽时间")
    private Timestamp fDatetime;
}
