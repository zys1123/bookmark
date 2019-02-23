package com.bookmark.controller;

import com.bookmark.entity.*;
import com.bookmark.entity.response.ResponseData;
import com.bookmark.service.CollectService;
import com.bookmark.service.ConfigService;
import com.bookmark.service.FavoritesService;
import com.bookmark.service.FollowService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 12425 on 2018/8/13.
 */
@Controller
public class CollectController extends BaseController {

    @Autowired
    CollectService collectService;

    @ApiOperation(value = "添加书签的方法")
    @RequestMapping(value = "/collect",method = RequestMethod.POST)
    @ResponseBody
    public Object collect(@Valid Collect collect,BindingResult result,String newFavorites){
        Object responseObj = getErrorMessage(result);
        if(responseObj != null){
            return  responseObj;
        }
        try {
            collectService.insertCollect(collect,newFavorites);
            Map map = new HashMap<String,String>();
            map.put("url","/home");
            responseObj = response("1","添加成功",map);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseObj = response("0","添加失败",new HashMap<>().put("reason",e.getMessage()));
        }
        return responseObj;
    }

    @ApiOperation(value = "得到collect的详细情况")
    @RequestMapping(value = "/collect/{id}",method =RequestMethod.GET)
    @ResponseBody
    public Object getCollectById(@PathVariable("id") Long id){
        Collect collect = collectService.getCollectById(id);
        return collect;
    }

    @ApiOperation(value = "更新collect")
    @RequestMapping(value = "/collect",method = RequestMethod.PUT)
    @ResponseBody
    public Object updateCollect(@Valid Collect collect, BindingResult result){
        //可供用户更改的属性为：category、favoritesId、remark、title、type、description
        //其余的属性如：charset、logoUrl、url、userId、creatTime还是沿用原来的
        //lastModify更新到此时
        //上述说的放到service层去包装吧
        ResponseData responseData = null;
        Object errorObj = getErrorMessage(result);
        if(errorObj != null){
            return  errorObj;
        }
        try{
            collectService.updateCollect(collect);
            responseData = response("1","更新成功",null);
        }catch(Exception e){
            responseData = response("0","删除失败",new HashMap<>().put("reason",e.getMessage()));
            logger.error(e.getMessage());
        }
        return  responseData;
    }

    @ApiOperation(value = "删除collect")
    @RequestMapping(value = "/collect", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteCollect(Collect collect){
        ResponseData responseData = null;
        try{
            collectService.deleteCollect(collect);
            responseData =  response("1","删除成功",null);
        }catch(Exception e){
            responseData = response("0","删除失败",new HashMap<>().put("reason",e.getMessage()));
            logger.error(e.getMessage());
        }
        return  responseData;
    }

}
