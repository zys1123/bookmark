package com.bookmark.controller;

import com.bookmark.entity.Favorites;
import com.bookmark.service.FavoritesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * Created by 12425 on 2018/8/14.
 */
@Controller
public class FavoritesController extends BaseController{

    @Autowired
    FavoritesService favoritesService;

    @ApiOperation(value = "增加收藏夹")
    @RequestMapping(value = "/favorites", method = RequestMethod.POST)
    @ResponseBody
    public Object addFavorites(@Valid Favorites favorites, BindingResult result){
        Object responseObj = getErrorMessage(result);
        if(responseObj!=null){
            return  responseObj;
        }
        try {
            favoritesService.createDefaultFavorites(favorites.getName(), getUserId());
            responseObj =  response("1","增加成功",null);
        }catch(Exception e){
            responseObj = response("0","增加失败",new HashMap<>().put("reason",e.getMessage()));
            logger.error(e.getMessage());
        }
        return responseObj;
    }
    /*
    @ApiOperation(value ="修改收藏夹")
    @RequestMapping(value = "/favorites",method = RequestMethod.PUT)
    @ResponseBody
    public Object updateFavorites(Favorites favorites){
        try{
            favoritesService.
        }
    }*/

}
