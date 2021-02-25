package com.example.demonote.service;

import com.example.demonote.domain.User;

public interface UserService {
    //登录
    User login(String username,String password);
}
