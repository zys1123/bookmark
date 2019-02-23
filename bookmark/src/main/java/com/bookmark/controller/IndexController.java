package com.bookmark.controller;

import com.bookmark.entity.*;
import com.bookmark.entity.view.CollectView;
import com.bookmark.entity.view.FavoritesView;
import com.bookmark.service.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 12425 on 2018/8/9.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    ConfigService configService;

    @Autowired
    FavoritesService favoritesService;

    @Autowired
    FollowService followService;

    @Autowired
    CollectService collectService;

    @Autowired
    Const aConst;

    @Autowired
    UserService userService;

    /**
     * 当用户没有collect时，展示例子给用户，当有collect时，将collect展示给用户。
     * @param model
     * @return
     */
    @ApiOperation(value = "默认的登陆后的界面")
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String toHome(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Config config = configService.findConfigByUserId(user.getId());
        Map<String,List<Follow>> followMap = followService.findFollowByUserId(user.getId());
        Favorites favorites = favoritesService.findFavoritesById(Long.valueOf(config.getDefaultFavorties()));
        List<FavoritesView> favoritesViewList = favoritesService.getFavoritesViewList(getUserId());
        List<Favorites> favoritesList = favoritesService.findFavoritesByUserId(getUserId());
        int size = collectService.getUserCollectCount(user.getId());
        model.addAttribute("configObj",config);
        model.addAttribute("config",config);
        model.addAttribute("user",user);
        model.addAttribute("favorites",favorites);
        model.addAttribute("followMap",followMap);
        model.addAttribute("size",size);
        model.addAttribute("favoritesList",favoritesList);
        model.addAttribute("favoritesViewList",favoritesViewList);
        model.addAttribute("followList",new ArrayList<>());
        model.addAttribute("newAtMeCount",0);
        model.addAttribute("newCommentMeCount",0);
        model.addAttribute("newPraiseMeCount",0);
        return "home";
    }

    @ApiOperation(value = "添加书签的小工具的源码")
    @RequestMapping(value="/tool")
    public String tool(Model model) {
        String path="javascript:(function()%7Bvar%20description;var%20desString=%22%22;var%20metas=document.getElementsByTagName('meta');for(var%20x=0,y=metas.length;x%3Cy;x++)%7Bif(metas%5Bx%5D.name.toLowerCase()==%22description%22)%7Bdescription=metas%5Bx%5D;%7D%7Dif(description)%7BdesString=%22&amp;description=%22+encodeURIComponent(description.content);%7Dvar%20win=window.open(%22"
                + aConst.getPath()
                +"addCollect?from=webtool&url=%22+encodeURIComponent(document.URL)+desString+%22&title=%22+encodeURIComponent(document.title)+%22&charset=%22+document.charset,'_blank');win.focus();%7D)();";
        model.addAttribute("path",path);
        return "tool";
    }

    @ApiOperation(value = "查看某个用户的favorites的文章")
    @RequestMapping(value = "/simple/{type}/{userId}")
    public String simple(Model model, @RequestParam(value = "page",defaultValue = "0")Integer page,
                         @RequestParam(value = "size",defaultValue = "20")Integer size,@PathVariable("type") Long type,
                         @PathVariable("userId") Long userId){

        PageInfo<Collect> pageInfo = collectService.getCollectPageInfoByUserIdAndFavoritesId(userId,type,page,size);
        Favorites favorites = favoritesService.findFavoritesById(type);
        User user = userService.findUserById(userId);
        List<CollectView> collectViewList = collectService.getCollectViews(pageInfo.getList(),user,favorites);
        model.addAttribute("collects",collectViewList);
        model.addAttribute("favorites",favorites);
        model.addAttribute("size",pageInfo.getList().size());
        model.addAttribute("user",getUserId());
        model.addAttribute("type","my");
        return "collect/standard";
    }

    @ApiOperation(value = "首页加载用户的全部文章")
    @RequestMapping(value = "/loadPage/{type}/{userId}")
    public String loadPage(Model model, @RequestParam(value = "page",defaultValue = "0")Integer page,
                         @RequestParam(value = "size",defaultValue = "20")Integer size,@PathVariable("type") Long type,
                         @PathVariable("userId") Long userId){
        PageInfo<DatabaseCollect> pageInfo = collectService.getCollectFavoritesByUserId(userId,type,page,size);
        List<CollectView> collectViewList = collectService.getCollectViews(pageInfo.getList());
        model.addAttribute("size",collectViewList.size());
        model.addAttribute("collects",collectViewList);
        return "standard";
    }

    @ApiOperation(value = "添加书签的页面")
    @RequestMapping(value = "/addCollect",method = RequestMethod.GET)
    public String collect(Model model){
        Map<String,List<Follow>> stringListMap = followService.findFollowByUserId(getUserId());
        List<Favorites> favoritesList = favoritesService.findFavoritesByUserId(getUserId());
        Config config = configService.findConfigByUserId(getUserId());
        model.addAttribute("favoritesList",favoritesList);
        model.addAttribute("followList",stringListMap.get("follows"));
        model.addAttribute("configObj",config);
        return "collect";
    }

    @ApiOperation(value = "跳转到新建收藏夹的界面")
    @RequestMapping(value = "/newfavorites")
    public String newfavorites(){
        return "favorites/newfavorites";
    }

}
