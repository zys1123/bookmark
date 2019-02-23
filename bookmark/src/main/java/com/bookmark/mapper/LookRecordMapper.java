package com.bookmark.mapper;

import com.bookmark.entity.LookRecord;

public interface LookRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LookRecord record);

    int insertSelective(LookRecord record);

    LookRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LookRecord record);

    int updateByPrimaryKey(LookRecord record);
}