package com.bookmark.service;

import com.bookmark.mapper.PraiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 12425 on 2018/8/9.
 */
@Service
public class PraiseService {

    @Autowired
    PraiseMapper praiseMapper;

    public int getPraiseCountByCollectId(Long id){
        return praiseMapper.getPraiseCountByCollectId(id);
    }

}
