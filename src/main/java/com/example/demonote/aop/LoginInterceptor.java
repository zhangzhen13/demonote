package com.example.demonote.aop;

import com.example.demonote.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 1、程序启动后，网页访问项目，先执行拦截器【WebConfigurer】
 * 2、执行LoginInterceptor(当前类)，判断session里面是否有内容【登录后应该是 把 isLogined 赋值了，所以这里判断有没有值，没值就是没登录】，有就进入UserController中的login方法，经过判断进入login.html，
 * 3、没有就继续走下一步，也就是UserController类中的【loginForm】方法然后进入列表中。
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

//    @Autowired
//    private LoginDao dao;
    //这个方法是在访问接口之前执行的，我们只需要在这里验证登录状态的业务逻辑，就可以在用户指定接口之前验证登录状态了
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  throws Exception{
    /*    //获得cookie
        Cookie[] cookies = request.getCookies();
        //没有cookie信息，则重定向到登录界面
        if (null ==null){
            response.sendRedirect(request.getContextPath()+"/login");
            return  false;
        }
        //定义cookie_username,用户的一些登录信息，例如：用户名，密码等
        String cookie_username = null;
        //获取cookie里面的一些用户信息
        for (Cookie item : cookies){
            if ("cookie_username".equals(item.getName())){
                cookie_username = item.getValue();
                break;
            }
        }
        //如果cookie里面没有包含用户的一些登录信息，则重定向到登录界面
        if (StringUtils.isEmpty(cookie_username)){
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        //获取HTTPSession对象
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        if (null == obj){
            //根据用户登录账号获取数据库中的用户信息
//            User user =
        }
*/
//老版登录拦截器
        //判断session中是否存在用户（是否登录)
        HttpSession session = request.getSession();
//        User user = (User)request.getSession().getAttribute("user1");
//        if (user ==null){
            //未登录，返回登录页面
//            request.setAttribute("msg","没有权限请先登录");
//            if (session.getAttribute("user")==null){
            //请求转发到登录界面

        if (session.getAttribute("isLogined")==null){//获取UserController中设置的isLogined值
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }else {
            return true;
        }


 }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{

    }
}