package com.bookmark.utils;

import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 12425 on 2018/8/8.
 */
public class WebUtils {

    public static boolean isAjaxRequest(HttpServletRequest request){
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    public static void saveRequest(HttpServletRequest request){
        SecurityUtils.getSubject().getSession().setAttribute("request",request);
    }

    public static void writeResponse(HttpServletResponse response,Object obj) throws IOException{
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSON(obj).toString());
        out.flush();
    }

}
