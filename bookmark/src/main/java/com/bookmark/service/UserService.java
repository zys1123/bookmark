package com.bookmark.service;

import com.bookmark.entity.Favorites;
import com.bookmark.entity.User;
import com.bookmark.mapper.UserMapper;
import com.bookmark.utils.DateFormatUtils;
import com.bookmark.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 12425 on 2018/8/8.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    FavoritesService favoritesService;

    @Autowired
    ConfigService configService;

    public User findUserByUsernameOrEmail(String userName){
        if(userName==null) userName = "";
        return userMapper.findUserByUsernameOrEmail(userName);
    }

    public boolean findUsersByUsernameOrEmail(String username, String email){
        if(findUserByUsernameOrEmail(username)!=null && findUserByUsernameOrEmail(email)!=null){
            return false;
        }
        return true;
    }

    @Transactional
    public long insertUser(User user) throws Exception{
        if (!findUsersByUsernameOrEmail(user.getUserName(), user.getEmail())) {
            throw new Exception("用户名或者邮箱已经被占用");
        }
        user.setPassWord(MD5Util.md5(user.getPassWord()));
        user.setCreateTime(DateFormatUtils.getCurrentDateLong());
        user.setLastModifyTime(DateFormatUtils.getCurrentDateLong());
        user.setProfilePicture("/img/favicon.png");
//        int userId = insertUser(user);
        userMapper.insert(user);
        //添加默认的收藏夹
        Favorites f = favoritesService.createDefaultFavorites("未读列表",user.getId());
        configService.createDefaultConfig(user.getId(),f.getId());
        return user.getId();
    }

    @Transactional
    public void updateUser(User user) throws Exception{
        User dbUser = findUserByUsernameOrEmail(user.getUserName());
        if(dbUser == null){
            throw new Exception("没有找对对应的用户");
        }
        dbUser.setPassWord(MD5Util.md5(user.getPassWord()));
        userMapper.updateByPrimaryKey(dbUser);
    }

    public User findUserById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }
}
