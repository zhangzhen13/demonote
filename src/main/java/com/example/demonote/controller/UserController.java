package com.example.demonote.controller;

import com.example.demonote.domain.User;
import com.example.demonote.domain.note;
import com.example.demonote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService service;

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

                return "redirect:/note/noteList";
            }else {
                return "login";
            }
        }
        return "login";

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

    }
}