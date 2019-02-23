package com.bookmark.mapper;

import com.bookmark.entity.Collect;
import com.bookmark.entity.DatabaseCollect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKeyWithBLOBs(Collect record);

    int updateByPrimaryKey(Collect record);

    Integer getUserCollectCount(@Param("userId")Long userId);

    List<Collect> getCollectByUserIdAndFavoritesId(@Param("userId")Long userId,@Param("favoritesId")Long favoritesId);

    List<DatabaseCollect> getCollectFavoritesByUserId(@Param("userId")Long userId,@Param("favoritesId")Long
            favoritesId);
}