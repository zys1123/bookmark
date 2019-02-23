package com.bookmark.entity.view;

import com.bookmark.entity.Favorites;

import java.io.Serializable;

/**
 * Created by 12425 on 2018/8/14.
 */
public class FavoritesView implements Serializable {

    private Long id;

    private String name;

    private String url;

    private Long userId;

    public FavoritesView(Favorites favorites) {
        this.id = favorites.getId();
        this.name = favorites.getName();
        this.userId = favorites.getUserId();
        this.url = "/simple/"+favorites.getId()+"/"+favorites.getUserId();
    }

    public FavoritesView(Long id, String name, String url, Long userId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
