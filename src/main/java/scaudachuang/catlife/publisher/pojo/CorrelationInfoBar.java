package scaudachuang.catlife.publisher.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 *
 * 用于展示用户的粉丝、黑名单
 *
 * 展示名字、头像、关注拉黑日期
 * id用于查询该用户的主页
 * */
@Data
public class CorrelationInfoBar {
    private int id;
    private Timestamp fbDateTime;
    private String nickName;
    private String avatar;
}
