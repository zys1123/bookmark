package com.bookmark.mapper;

import com.bookmark.entity.Follow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Follow record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);

    List<Follow> findFollowByUserId(@Param("userId") Long userId);

    List<Follow> findFollowedByUserId(@Param("userId") Long userId);

}