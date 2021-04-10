package scaudachuang.catlife.publisher.dao;

import org.apache.ibatis.session.RowBounds;
import scaudachuang.catlife.publisher.entity.CatLifeRecord;
import scaudachuang.catlife.publisher.pojo.CorrelationInfoBar;

import java.util.List;

public interface UserMapper {
    /*分页，通过id查找自己的粉丝*/
    List<CorrelationInfoBar> getUserFollowedInfoBar(
            int id,
            RowBounds rowBounds);
    /*黑名单同理*/
    List<CorrelationInfoBar> getUserBlackListedInfoBar(
            int id,
            RowBounds rowBounds
    );

}
