package com.example.demonote.controller;

import com.example.demonote.domain.User;
import com.example.demonote.domain.note;
import com.example.demonote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String login(HttpSession session){
        if(session.getAttribute("user")!=null){
            return "redirect:/note/noteList";
        }else {
            return "login";
        }
    }
    @RequestMapping("loginForm")
    public String logins(Model model, String username, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
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
                Boolean rememberMe = Boolean.parseBoolean(request.getParameter("rememberMe"));//判断是否勾选了【记住我】
                if (rememberMe){
//                    session.setMaxInactiveInterval(1*24*60*60);//设置session持久化时间。1天
                    Cookie cookie = new Cookie("JSESSIONID",session.getId());//创建cookie，设置cookie的键值
//                    cookie.setPath(request.getContextPath());//指定cookie的路径，路径必须要添加上项目名称
                    System.out.println("cookie1"+session.getId());
                    System.out.println("cookie1 value"+cookie.getValue());

                    cookie.setMaxAge(1*1*60*60);//设置cookie的有效期
                    response.addCookie(cookie);//向响应中添加cookie
//                    return "redirect:/note/noteList";

                }else{
//                session.setMaxInactiveInterval(1*24*60*60);//设置session持久化时间。30天
                    Cookie cookie2 =  new Cookie("JSESSIONID",session.getId());
//                    cookie2.setPath("http://localhost:8009");
                    System.out.println("cookie2:"+session.getId());

                    cookie2.setMaxAge(5*60);
                    response.addCookie(cookie2);
//                    return "redirect:/note/noteList";

                }
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
//        sessionname = new Session("sess","");
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

