package com.bookmark.service;

import com.bookmark.entity.Favorites;
import com.bookmark.entity.view.FavoritesView;
import com.bookmark.mapper.FavoritesMapper;
import com.bookmark.utils.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 12425 on 2018/8/9.
 */
@Service
public class FavoritesService {

    @Autowired
    FavoritesMapper favoritesMapper;

    public Favorites createDefaultFavorites(String name,Long userId){
        Favorites favorites = new Favorites();
        favorites.setName(name);
        favorites.setUserId(userId);
        favorites.setCount(Long.valueOf("0"));
        favorites.setPublicCount(Long.valueOf("0"));
        favorites.setCreateTime(Long.valueOf(DateFormatUtils.getCurrentDateString()));
        favorites.setLastModifyTime(Long.valueOf(DateFormatUtils.getCurrentDateString()));
        createFavorites(favorites);
        return favorites;
    }
    public int createFavorites(Favorites favorites){
        return favoritesMapper.insert(favorites);
    }

    public Favorites findFavoritesById(Long id){
        return  favoritesMapper.selectByPrimaryKey(id);
    }


    public List<FavoritesView> getFavoritesViewList(Long id){
        List<Favorites>  favoritesList = favoritesMapper.findFavoritesByUserId(id);
        List<FavoritesView> favoritesViewList = new LinkedList<>();
        for(Favorites f : favoritesList){
            favoritesViewList.add(new FavoritesView(f));
        }
        favoritesViewList.add(new FavoritesView(Long.valueOf("0"),"新建收藏夹","/newfavorites",id));
        return favoritesViewList;
    }

    public List<Favorites> findFavoritesByUserId(Long id){
        return favoritesMapper.findFavoritesByUserId(id);
    }
}
