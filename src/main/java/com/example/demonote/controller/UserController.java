package com.example.demonote.controller;

import com.example.demonote.domain.User;
import com.example.demonote.domain.note;
import com.example.demonote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("/")
    public String hello(){
        return "login";
    }
    //进入登录界面
    @RequestMapping("login")
    public String login(){
        return "login";
    }
    @RequestMapping("loginForm")
    public String logins(Model model, String username, String password, HttpSession session) {
        User user = service.login(username,password);

        if (user!=null){
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
//                User user1 = new User();
//                user1.setUsername(username);
//                user1.setPassword(password);
//                session.setAttribute("user",user1);
                Boolean isLogined = true;
                session.setAttribute("isLogined",isLogined);//设置isLogined值，在LoginInterceptor中获取
                String hello = "你好！";
                session.setAttribute("hello",hello);
                session.setAttribute("username",username);//设置username值，在各个页面上获取用户名并显示
                session.setAttribute("user",user);//设置user，用于退出该用户
                session.setAttribute("id",user.getId());
                return "redirect:/note/noteList";
            }else {
                return "login";
            }
        }
        return "login";
    }
    //账号退出
    @GetMapping("loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");//退出登录则清除session中的用户信息
        return "login";
    }

}
        /**
         * 方式2
         *mapper
             //查询某用户
             @Select("select * from user where username = #{username}; ")
             User login(@Param("username") String username);
         service
             //登录
             User login(String username);
         impl
             //登录
             @Override
             public User login(String username) {
             return mapper.login(username);
             }
         controller
             //进入登录界面
             @RequestMapping("login")
             public String login(){
                return "login";
             }
             @RequestMapping("loginForm")
             public String logins(Model model, String username,String password) {
                User users = service.login(username);

                 if(users == null){
                     //用不不存在
                     return "login";
                 }else if(!password.equals(users.getPassword())){
                     //密码正确
                     return "login";
                 }
                 return "redirect:note/test";

         }
         * */

