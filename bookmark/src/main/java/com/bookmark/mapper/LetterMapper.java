package com.bookmark.mapper;

import com.bookmark.entity.Letter;

public interface LetterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Letter record);

    int insertSelective(Letter record);

    Letter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Letter record);

    int updateByPrimaryKeyWithBLOBs(Letter record);

    int updateByPrimaryKey(Letter record);
}