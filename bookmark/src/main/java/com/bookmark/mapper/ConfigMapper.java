package com.bookmark.mapper;

import com.bookmark.entity.Config;
import org.apache.ibatis.annotations.Param;

public interface ConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    Config findConfigByUserId(@Param("userId") Long userId);
}