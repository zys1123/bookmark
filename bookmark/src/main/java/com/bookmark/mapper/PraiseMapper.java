package com.bookmark.mapper;

import com.bookmark.entity.Praise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PraiseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Praise record);

    int insertSelective(Praise record);

    Praise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Praise record);

    int updateByPrimaryKey(Praise record);

    int getPraiseCountByCollectId(@Param("collectId") Long collectId);

    List<Praise> getPraisesByCollectId(@Param("collectId") Long collectId);
}