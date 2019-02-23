package com.bookmark.service;

import com.bookmark.entity.Follow;
import com.bookmark.mapper.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 12425 on 2018/8/9.
 */
@Service
public class FollowService {

    @Autowired
    FollowMapper followMapper;

    public Map<String,List<Follow>> findFollowByUserId(Long userId){
        List<Follow> follows =  followMapper.findFollowByUserId(userId);
        List<Follow> followeds = followMapper.findFollowedByUserId(userId);
        Map<String,List<Follow>> map = new HashMap<>();
        map.put("follows",follows);
        map.put("followeds",followeds);
        return map;
    }

}
