package com.bookmark.service;

import com.bookmark.entity.Config;
import com.bookmark.mapper.ConfigMapper;
import com.bookmark.utils.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 12425 on 2018/8/9.
 */
@Service
public class ConfigService {

    @Autowired
    ConfigMapper configMapper;

    public void createDefaultConfig(Long userId,Long favoritesId){
        Config config = new Config();
        config.setCreateTime(Long.valueOf(DateFormatUtils.getCurrentDateString()));
        config.setLastModifyTime(Long.valueOf(DateFormatUtils.getCurrentDateString()));
        config.setUserId(userId);
        config.setDefaultFavorties(String.valueOf(favoritesId));
        config.setDefaultCollectType("public");
        config.setDefaultModel("simple");
        configMapper.insert(config);
    }

    public Config findConfigByUserId(Long userId){
        return configMapper.findConfigByUserId(userId);
    }

}
