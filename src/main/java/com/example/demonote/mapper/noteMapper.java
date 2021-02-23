package com.example.demonote.mapper;//mapper层（Mybatis）dao 数据访问层

import com.example.demonote.domain.note;
import org.apache.ibatis.annotations.*;
//import org.omg.CosNaming.NamingContextPackage.NotEmpty;
/*
* alter table note add isDelete int(1) not null default '0';
#逻辑删除语句：删除时 update user set deleted=1 where id =1 and deleted=0查找时 select * from user where deleted=0
update note set isDelete =1 where id = 23;
select * from note where isDelete =0;

isDelete =0,默认为0 ，当=1时则标记为删除
* */
import java.util.List;

@Mapper
public interface noteMapper {
    //查询全部
    @Select("select * from note where isDelete = 0")
    List<note> selectAllnote();

    //新增数据
    @Insert("insert into note(name,content) values(#{name},#{content})")
    public void addNote(note note);
    //删除数据
//    @Delete("delete from note where id =#{id}")
//    public int delete(int id);
    @Update("update note set isDelete =1 where id =#{id}")
    public int deleted(int id);
//    @Delete("delete from note where id =#{id}")
//    public int deleteNote(note note);
    //根据id进行查找（用于修改）111111111111111111
    @Select("select * from note where id = #{id}")
    public note get(int id);

    //修改数据
    @Update("update note set name = #{name},content = #{content} where id =#{id}")
    public int update(note note);


    //分页查询
    @Select("select * from note where isDelete =0 limit #{pageNumber},#{pageSize}")//pageNo起始数据位置 pageSize每页条数
    List<note> selectPage(@Param("pageNumber") int pageNumber,@Param("pageSize")int pageSize);
    //List<note> selectPage(@Param("pageNumber") int pageNumber,@Param("pageSize")int pageSize);
    //@Results({
    //        @Result(property = "id", column = "id"),
    //        @Result(property = "pageNumber", column = "pageNumber"),
    //        @Result(property = "pageSize", column = "pageSize"),
    //})
    //查询总条数
    @Select("select count(*) from note where isDelete = 0")
    public int getCount();
}