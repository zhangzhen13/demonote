package com.example.demonote.service;//service层

import com.example.demonote.domain.note;

import java.util.List;

public interface noteService {
    //查询
    List<note> selectAllnote();
    //新增
    void addNote(note note);
    //查找某条数据1111111111111111111获取id
    note get(int id);
    //根据查找的数据进行修改
    int update(note note);
    //删除
    int deleted(int id);

    //分页查询
    List<note> selectPage(int pageNumber,int pageSize);
    //查询总条数
    int getCount();
}
