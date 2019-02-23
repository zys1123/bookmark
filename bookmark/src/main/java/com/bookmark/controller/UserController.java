package com.bookmark.controller;

import com.bookmark.entity.Favorites;
import com.bookmark.entity.User;
import com.bookmark.service.ConfigService;
import com.bookmark.service.FavoritesService;
import com.bookmark.service.UserService;
import com.bookmark.utils.DateFormatUtils;
import com.bookmark.utils.MD5Util;
import com.bookmark.valid.LoginCheck;
import com.bookmark.valid.RegisterCheck;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 12425 on 2018/8/8.
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    UserService userService;


    @ApiOperation(value = "跳转到登陆界面")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    @ApiOperation(value = "用户登陆使用的方法")
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(@Validated(value = LoginCheck.class) User user,
                        BindingResult result){
        Object responseObj = getErrorMessage(result);
        if(responseObj != null){
            return  responseObj;
        }
        user.setPassWord(MD5Util.md5(user.getPassWord()));
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            HttpServletRequest request = (HttpServletRequest) subject.getSession().getAttribute("request");
            Map<String, String> map = new HashMap<>();
            if(request!=null) {
                String rurl = request.getRequestURI();
                //如果不是一下的跳转
                if(rurl==null || (rurl.indexOf("/collect?") < 0 && rurl.indexOf("/lookAround/standard/") < 0
                        && rurl.indexOf("/lookAround/simple/") < 0)){
                    rurl = "/loadPage/0/"+getUserId();
                    map.put("rurl",rurl);
                }
                else if(rurl.indexOf("/lookAround/standard/") >= 0){
                    rurl = "/lookAround/standard/ALL";
                    map.put("rurl",rurl);
                }
                else if(rurl.indexOf("/lookAround/simple/") >= 0){
                    rurl = "/lookAround/simple/ALL";
                    map.put("rurl",rurl);
                }
                if(map.get("rurl")==null){
                    map.put("rurl",request.getRequestURI());
                }
            }else {
                map.put("rurl","/home");
            }
            responseObj = response("1","登陆成功",map);
        }catch(Exception e){
            //登陆失败而已，不用记日志了吧
            //此处抛出异常交由统一异常处理吧。
            responseObj =  response("0","用户名密码错误",new HashMap<>().put("reason",e.getMessage()));
        }
        return  responseObj;
    }

    @ApiOperation(value = "跳转到注册的页面")
    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @ApiOperation(value = "用户注册的方法")
    @ResponseBody
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Object register(@Validated(value = RegisterCheck.class) User user,BindingResult result){
        Object responseObj = getErrorMessage(result);
        if(responseObj != null){
            return  responseObj;
        }
        try {
            userService.insertUser(user);
            responseObj = response("1","注册成功",null);
        }catch(Exception e){
            responseObj = response("0","注册失败",new HashMap<>().put("reason",e.getMessage()));
            logger.error(e.getMessage());
        }
        return responseObj;
    }

    @ApiOperation(value = "修改密码")
    @ResponseBody
    @RequestMapping(value = "resetPwd",method = RequestMethod.POST)
    public Object resetPwd(@Validated(value = LoginCheck.class) User user,BindingResult result){
        Object responseObj = getErrorMessage(result);
        if(responseObj != null){
            return  responseObj;
        }
        try {
            userService.updateUser(user);
            responseObj = response("1","修改密码成功",null);
        }catch(Exception e){
            responseObj = response("0","修改密码失败",new HashMap<>().put("reason",e.getMessage()));
        }
        return  responseObj;
    }

}
