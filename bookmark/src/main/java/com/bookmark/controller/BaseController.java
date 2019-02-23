package com.bookmark.controller;

import com.bookmark.entity.response.ResponseData;
import com.bookmark.entity.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import java.util.Locale;

/**
 * Created by 12425 on 2018/8/8.
 */
@Controller
public class BaseController {

    /**
     * 数据校验用的返回消息
     */
    @Autowired
    MessageSource messageSource;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ResponseData response(String code,String msg,Object data){
        return new ResponseData(code, msg,data);
    }

    protected  Long getUserId(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user != null){
            return user.getId();
        }
        return  null;
    }

    /**
     * 返回error中对应的错误信息
     * @param result
     * @return
     */
    protected  Object getErrorMessage(BindingResult result){
        if(result.hasErrors()){
            Locale currentLocale = LocaleContextHolder.getLocale();
            return response("-1",messageSource.getMessage(result
                    .getFieldErrors().get(0),currentLocale),null);
        }
        return null;
    }

    /**
     * 检查下是否是本人的操作，在对书签、收藏夹 修改，删除的时候检查
     * @param userId
     * @return
     */
    protected boolean isMyselfOperation(Long userId){
        return getUserId()==null?false:getUserId().equals(userId);
    }

}
