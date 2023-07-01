package com.peter.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 3. ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //除了login页面以外其他页面都需要认证才能访问，也就是不登录啥都看不了
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/*","authc");
        //添加权限控制，只有指定账号有权限更改employee信息
        filterMap.put("/emp/*","perms[manager]");
        filterMap.put("/delEmp/*","perms[manager]");
        bean.setFilterChainDefinitionMap(filterMap);

        // 设置登录请求
        bean.setLoginUrl("/toLogin");

        // 未授权页面
        bean.setUnauthorizedUrl("/unauthorized");

        return bean;
    }

    // 2. DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //连接userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 1. 创建Realm对象(首先）
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
