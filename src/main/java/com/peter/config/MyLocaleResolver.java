package com.peter.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//用来配置登陆页面中英文切换的
public class MyLocaleResolver implements LocaleResolver {

    //解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求的链接里带"l"的里面 l = 的内容
        String lang = request.getParameter("l");
        Locale locale = Locale.getDefault();

        //如果这个l 不为空，也就是请求的链接里带了国际化参数，那就把语言分割出来并重新传给locale然后返回
        if(StringUtils.hasText(lang)){
            String[] s = lang.split("_");
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }


    //这个不用重写
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
