package com.bookmark.shiro;

import com.bookmark.entity.User;
import com.bookmark.utils.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆的过滤器
 * Created by 12425 on 2018/8/8.
 */
public class LoginFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user != null){
            return true;
        }
        WebUtils.saveRequest((HttpServletRequest) request);
        if(WebUtils.isAjaxRequest((HttpServletRequest) request)){
            Map<String,String> result = new HashMap<>();
            result.put("code","-1");
            result.put("msg","您还没有登陆");
            result.put("rurl","/login");
            WebUtils.writeResponse((HttpServletResponse) response,result);
        }
        //重定向
        ((HttpServletResponse) response).sendRedirect("/login");

        //转发
        //((HttpServletRequest) request).getRequestDispatcher("login").forward(request,response);

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }
}
