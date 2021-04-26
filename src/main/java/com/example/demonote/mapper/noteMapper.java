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

    //新增数据insert into Note(`name`,content,uId) values('焉能不贵复不去，空作昂藏一丈夫','记事3',3);
    @Insert("insert into note(name,content,uId) values(#{name},#{content},#{uId})")
    public void addNote(note note);
    //删除数据
//    @Delete("delete from note where id =#{id}")
//    public int delete(int id);
    @Update("update note set isDelete =1 where id =#{id} and uId = #{uId}")
    public int deleted(@Param("id") int id,@Param("uId") int uId);
//    @Delete("delete from note where id =#{id}")
//    public int deleteNote(note note);
    //根据id进行查找（用于修改）111111111111111111
    @Select("select * from note where id = #{id}")
    public note get(int id);

    //修改数据
    @Update("update note set name = #{name},content = #{content} where id =#{id}")
    public int update(note note);


    //分页查询
    @Select("select * from note where isDelete =0 limit #{pageNumber},#{pageSize}")//pageNumber起始数据位置 pageSize每页条数
    List<note> selectPage(@Param("pageNumber") int pageNumber,@Param("pageSize")int pageSize);
    //List<note> selectPage(@Param("pageNumber") int pageNumber,@Param("pageSize")int pageSize);
    //@Results({
    //        @Result(property = "id", column = "id"),
    //        @Result(property = "pageNumber", column = "pageNumber"),
    //        @Result(property = "pageSize", column = "pageSize"),
    //})
    //当前用户分页查询
    @Select("select note.* from note left join user on note.uId = user.id where user.id = #{id} and isDelete =0 limit #{pageNumber},#{pageSize}")
    List<note> selectUserPage(@Param("id") int id,@Param("pageNumber") int pageNumber,@Param("pageSize")int pageSize);
    //查询总条数
    @Select("select count(*) from note where isDelete = 0")
    public int getCount();
    //根据每个用户，查出各自列表
    @Select("select note.* from note left join user on note.uId = user.id where user.id = #{id}")
    List<note> userList(int id);

}