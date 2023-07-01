package com.peter.controller;

import com.peter.mapper.userMapper;
import com.peter.pojo.User;
import com.peter.service.userService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

/*    @Autowired
    private userService userService;

    //下面里面的model是回显数据用的
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session){

        //如果用户名不对，会有空指针异常的问题,未解决
        User user = userService.queryUserByName(username);
        if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","用户名或者密码错误");
            return "index";
        }
    }*/

    @RequestMapping("/toLogin")
    public String toLogin(){
        return"index";
    }

    //用shiro实现了登录控制功能
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "/index.html";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "/index.html";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "/index.html";
        }
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public String unauthorized(){
        return "未经授权无法访问";
    }

    //shiro实现logout
    @RequestMapping("/user/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index.html";
    }
    /*    @RequestMapping("/user/logout")
        public String logout(HttpSession session){
            session.invalidate();
            return "redirect:/index.html";
        }*/
}
