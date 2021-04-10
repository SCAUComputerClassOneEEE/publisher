package scaudachuang.catlife.publisher.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import scaudachuang.catlife.publisher.entity.Cat;
import scaudachuang.catlife.publisher.entity.HaveCat;
import scaudachuang.catlife.publisher.pojo.SimpleHaveCatInfoBar;

import java.util.List;

public interface CatMapper {
    Cat getCat(String cl);
    List<SimpleHaveCatInfoBar> getAllOwnerSimpleHaveCatById(int ownerId, RowBounds rowBounds);
    List<SimpleHaveCatInfoBar> getAllOwnerSimpleHaveCatByIdAndCatClass(
            @Param("ownerId") int ownerId,
            @Param("catClass") String catClass,
            RowBounds rowBounds
    );

    HaveCat getOneHaveCat(
            @Param("ownerId") int ownerId,
            @Param("catClass") String catClass,
            @Param("haveCatId") int haveCatId,
            RowBounds rowBounds
    );
}
