package com.peter.config;

import com.peter.config.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer{
    //视图控制
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // localhost:8080/  页面直接转去登录页
        // LoginController里如果用户登录成功会redirect到main.html，然后通过addViewController把/main.html跳转到dashboard
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器，及拦截请求和要剔除哪些请求!
        //我们还需要过滤静态资源文件，否则样式显示不出来
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login","/css/*","/js/*","/img/*");
    }

    //自定义国际化组件生效了
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
