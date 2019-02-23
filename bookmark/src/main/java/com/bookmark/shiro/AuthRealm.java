package com.bookmark.shiro;

import com.bookmark.entity.User;
import com.bookmark.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 12425 on 2018/8/8.
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    //没有注入进来

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        //从数据库或者缓存中获取角色数据
        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> sets = new HashSet<>();
        sets.add("user : delete");
        sets.add("user : add");
        return sets;
    }

    private Set<String> getRolesByUserName(String userName) {
//        Set<String>
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken uepToken = (UsernamePasswordToken) token;
        String username = uepToken.getUsername();
        User user = userService.findUserByUsernameOrEmail(username);
        if(user == null){
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user,user.getPassWord(),user.getUserName());
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
