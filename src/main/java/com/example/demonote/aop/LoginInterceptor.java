package com.example.demonote.aop;

import com.example.demonote.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    //这个方法是在访问接口之前执行的，我们只需要在这里验证登录状态的业务逻辑，就可以在用户指定接口之前验证登录状态了
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  throws Exception{
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