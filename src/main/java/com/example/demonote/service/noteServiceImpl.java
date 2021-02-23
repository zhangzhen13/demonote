package com.example.demonote.service;

import com.example.demonote.domain.note;
import com.example.demonote.mapper.noteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class noteServiceImpl implements noteService{
    @Autowired
    private noteMapper mapper;
    //查数据列表
    @Override
    public List<note> selectAllnote() {
        return mapper.selectAllnote();
    }
    //新增数据
    @Override
    public void addNote(note note) {
        mapper.addNote(note);
    }

    //根据id进行查找（用于修改）
    @Override
    public note get(int id) {
        return mapper.get(id);
    }

    //根据查找出来的id，进行修改
    @Override
    public int update(note note) {
        return mapper.update(note);
    }

    //删除数据
    @Override
    public int deleted(int id) {
        return mapper.deleted(id);
    }

    //分页查询
    @Override
    public List<note> selectPage(int pageNumber, int pageSize) {
        return mapper.selectPage(pageNumber,pageSize);
    }
    //获取总条数
    @Override
    public int getCount() {
        return mapper.getCount();
    }
}
