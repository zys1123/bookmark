package com.bookmark.mapper;

import com.bookmark.entity.Favorites;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoritesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Favorites record);

    int insertSelective(Favorites record);

    Favorites selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Favorites record);

    int updateByPrimaryKey(Favorites record);

    List<Favorites> findFavoritesByUserId(@Param("userId") Long userId);
}