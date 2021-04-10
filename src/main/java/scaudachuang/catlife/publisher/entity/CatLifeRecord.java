package scaudachuang.catlife.publisher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel(description = "每日记录")
public class CatLifeRecord {

    @ApiModelProperty(value = "主人的id")
    private int ownerId;
    @ApiModelProperty(value = "猫咪种类")
    private String catClass;
    @ApiModelProperty(value = "主人的同类猫的第几个猫")
    private int haveCatId;
    @ApiModelProperty(value = "该猫记录的记录时间")
    private Timestamp reDateTime;

    @ApiModelProperty(value = "成长", notes = "标签复合多值属性需要格式保存")
    private String growth;
    @ApiModelProperty(value = "饮食", notes = "标签复合多值属性需要格式保存")
    private String diet;
    @ApiModelProperty(value = "卫生", notes = "标签复合多值属性需要格式保存")
    private String hygiene;

    public static Map<String, String> getMapFromString(String s) {
        if (s == null) return null;
        if (s.length() == 0) return null;
        String[] splits = s.split(";");
        final Map<String, String> attMap = new HashMap<>();
        for (String split : splits) {
            String[] split1 = split.split(":");
            if (split1.length > 2)
                throw new IndexOutOfBoundsException("split1.length > 2");
            attMap.put(split1[0],split1[1]);
        }
        return attMap;
    }
}
