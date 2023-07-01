package com.peter.config;

import com.peter.pojo.User;
import com.peter.service.userService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    userService userservice;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权=》doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        System.out.println(user.getRole());
        info.addStringPermission(user.getRole());
        return info;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证=》doGetAuthorizationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //连接真实数据库
        User user = userservice.queryUserByName(token.getUsername());
        if(user == null){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
    }
}
