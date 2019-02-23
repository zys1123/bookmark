package com.bookmark.service;

import com.bookmark.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * Created by 12425 on 2018/8/17.
 */
@Service
public class BaseService {
    protected User getUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    protected boolean isMyselfOperation(Long userId){
        return getUser()==null?false:getUser().getId().equals(userId);
    }
}
