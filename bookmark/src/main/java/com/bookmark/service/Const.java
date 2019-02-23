package com.bookmark.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by 12425 on 2018/8/13.
 */
@Component
@ConfigurationProperties(prefix = "bookmark.base")
public class Const {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static String getBasePath(){
        Const c = new Const();
        return c.getPath();
    }
}
