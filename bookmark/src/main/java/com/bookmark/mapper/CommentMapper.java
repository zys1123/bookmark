package com.bookmark.mapper;

import com.bookmark.entity.Comment;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    int getCommentCountByCollectId(@Param("collectId")Long collectId);
}