package com.example.demonote.mapper;

import com.example.demonote.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    //查询某用户
    @Select("select * from user where username = #{username} and password = #{password}")
    User login(@Param("username") String username,@Param("password") String password);
}
