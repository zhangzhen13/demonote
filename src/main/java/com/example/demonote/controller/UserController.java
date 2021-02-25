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
    public String logins(Model model, String username,String password) {
        User user = service.login(username,password);

        if (user!=null){
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return "redirect:/note/test";
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