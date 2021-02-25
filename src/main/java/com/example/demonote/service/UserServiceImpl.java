package com.example.demonote.service;

import com.example.demonote.domain.User;
import com.example.demonote.mapper.UserMapper;
import com.example.demonote.mapper.noteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper mapper;

    //登录
    @Override
    public User login(String username,String password) {
        return mapper.login(username,password);
    }
}
