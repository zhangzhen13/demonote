package com.example.demonote.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    //这个方法是用来配置静态资源的，比如hetml,js，css等等

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
    //这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求，排出两个请求"/login",”/loginForm“,不需要拦截登录请求.添加是，把不能访问的路径添加进来.排除路径模式:excludePathPatterns;添加路径模式addPathPatterns
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/note/test","/note/addIndx","/note/addNote","/note/updatePage/{id}","/note/update","/note/delete/{id}","/note/details/{id}")
                .excludePathPatterns("/login","/loginFrom");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //发送login.html请求到
    }
}
