package com.bookmark.config;

import com.bookmark.shiro.AuthRealm;
import com.bookmark.shiro.LoginFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 12425 on 2018/8/7.
 */
@Configuration
public class ShiroConfiguration {
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<>();
        map.put("/login","anon");
        map.put("/collect*","login");
        map.put("/home","login");
        map.put("/favorites*","login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        Map<String,Filter> filterMap = new HashMap<>();
        filterMap.put("login",new LoginFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRememberMeManager(new CookieRememberMeManager());
        defaultWebSecurityManager.setRealm(authRealm);
        defaultWebSecurityManager.setSessionManager(new DefaultWebSessionManager());
        return defaultWebSecurityManager;
    }

    @Bean("authRealm")
    public AuthRealm authRealm(){
        return  new AuthRealm();
    }

}
