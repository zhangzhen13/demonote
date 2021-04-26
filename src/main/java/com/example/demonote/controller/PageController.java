package com.example.demonote.controller;

import com.example.demonote.domain.note;
import com.example.demonote.service.noteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class PageController {
    @Autowired
    private noteService service;
    //网址直接输入：http://localhost:8009/info?name=tom&age=10，控制台内能打印出name和age的值。html页面无任何内容。与下一种方式区别在于：请求的url的写法不一样。
    /**  * */
    @RequestMapping("info1")
    public String page( String name, int age){
        System.out.println("这是个消息");
         name = "tom";  age = 10;
        System.out.println(name+age);
        return "test";
    }
/**
 * //在地址里面可以不带参数，如果带了参数会接收，不带参数会默认为0
 *     @RequestMapping("/list")
 *     public String test(@RequestParam(defaultValue = "0") Long userId){
 *         return "";
 *     }
 * */
//    //是这样访问 浏览器输入localhost:8009/info/tom/10    与上一种方式区别在于：请求的url的写法不一样
//    @RequestMapping("info/{name}/{age}")
//    public String page(@PathVariable String name,@PathVariable int age){
//        System.out.println("这是个消息");
//
////       String name = "tom"; int age = 10;
//         System.out.println(name+age);
//        return "test";
//    }





/**
 * 分页方式第一版
 * //分页.首次分页，testhtml请求得到的分页功能   localhost:8009/note/test/1/10网址传参分页方式【1】
 *     @RequestMapping("/page/{pageNumber}/{pageSize}")
 *     public String page(Model model,@PathVariable int pageNumber,@PathVariable int pageSize){//pageNumber页码，pageSize条数
 *         int count = service.getCount();//总条数
 *         //总页数
 *         // int totalPage = count/pageSize;//总页数
 *         int totalPage;  //总页数
 *         if (count % pageSize == 0) {
 *             totalPage = count / pageSize;//总页数 当总条数除以每页显示数量结果无余数时，则总页数 = 总条数/每页条数
 *         }else {
 *             totalPage = count / pageSize + 1;//总页数 当总条数除以每页显示数量有余数时，则总页数 = 总条数/每页数 +1.除不尽的基础上多一页
 *         }
 *
 *         //判断页码最大值最小值，做一个限制
 *         if (pageNumber < 1){
 *             pageNumber = 1;
 *         }else if(pageNumber > totalPage){
 *             pageNumber = totalPage;
 *         }
 *         int starCurrentPage = (pageNumber - 1)* pageSize;//从第几个数据开始
 *         List<note> note =service.selectPage(starCurrentPage,pageSize);
 *         model.addAttribute("notelist" ,note);
 *         model.addAttribute("pageNumber",pageNumber);
 *         model.addAttribute("totalPage",totalPage);
 *         return "test";
 *     }
 *
 * 分页方式第二版
 * @RequestMapping("/test/{pageNumber}/{pageSize}")localhost:8009/note/test/1/10网址传参分页方式【1】
 *     public String hello(Model model,@PathVariable int pageNumber,@PathVariable int pageSize){
 * //        pageNumber = 0;
 * //        pageSize = 10;
 * //        page(model,0,10);
 * //        List<note> note =service.selectPage(pageNumber,pageSize);
 * //        model.addAttribute("list" ,note);
 *    //     return "index";
 *
 *         int count = service.getCount();//总条数
 *         //总页数
 *         // int totalPage = count/pageSize;//总页数
 *         int totalPage;  //总页数
 *         if (count % pageSize == 0) {
 *             totalPage = count / pageSize;//总页数 当总条数除以每页显示数量结果无余数时，则总页数 = 总条数/每页条数
 *         }else {
 *             totalPage = count / pageSize + 1;//总页数 当总条数除以每页显示数量有余数时，则总页数 = 总条数/每页数 +1.除不尽的基础上多一页
 *         }
 *         List<note> note;
 *         //判断页码最大值最小值，做一个限制
 *         if (pageNumber < 1){
 *             pageNumber = 1;
 * //            note =service.selectPage(pageNumber,pageSize);
 * //            model.addAttribute("list" ,note);
 *         }else if(pageNumber > totalPage){
 *             pageNumber = totalPage;
 * //            note =service.selectPage(pageNumber,pageSize);
 * //            model.addAttribute("list" ,note);
 *         }
 *         int starCurrentPage = (pageNumber - 1)* pageSize;//从第几个数据开始
 *         List<note> notes =service.selectPage(starCurrentPage,pageSize);
 *         model.addAttribute("list" ,notes);
 *         model.addAttribute("pageNumber",pageNumber);
 *         model.addAttribute("totalPage",totalPage);
 *         return "index";
 *     }
 * 分页方式第三版
 *   @RequestMapping("/info2")
 * //    @ResponseBody
 *     public String hello1(Model model ,@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
 *                          @RequestParam(name = "pageSize" ,defaultValue = "10") int pageSize) {
 *
 * //        return "name：" + name ;
 *         int count = service.getCount();//总条数
 *         //总页数
 *         // int totalPage = count/pageSize;//总页数
 *         int totalPage;  //总页数
 *         if (count % pageSize == 0) {
 *             totalPage = count / pageSize;//总页数 当总条数除以每页显示数量结果无余数时，则总页数 = 总条数/每页条数
 *         }else {
 *             totalPage = count / pageSize + 1;//总页数 当总条数除以每页显示数量有余数时，则总页数 = 总条数/每页数 +1.除不尽的基础上多一页
 *         }
 *         List<note> note;
 *         //判断页码最大值最小值，做一个限制
 *         if (pageNumber < 1){
 *             pageNumber = 1;
 * //            note =service.selectPage(pageNumber,pageSize);
 * //            model.addAttribute("list" ,note);
 *         }else if(pageNumber > totalPage){
 *             pageNumber = totalPage;
 * //            note =service.selectPage(pageNumber,pageSize);
 * //            model.addAttribute("list" ,note);
 *         }
 *         int starCurrentPage = (pageNumber - 1)* pageSize;//从第几个数据开始
 *         List<note> notes =service.selectPage(starCurrentPage,pageSize);
 *         model.addAttribute("list" ,notes);
 *         model.addAttribute("pageNumber",pageNumber);
 *         model.addAttribute("totalPage",totalPage);
 *         return "index";
 *     }
 *
 *      noteMapper
 *        //删除数据原删除方式
 * //    @Delete("delete from note where id =#{id}")
 * //    public int delete(int id);
 *     @Update("update note set isDelete =1 where id =#{id}")
 *     public int deleted(int id);
 *
 *
 *     NOteCOntroller
 *      //删除数据
 *     @RequestMapping("/delete/{id}")
 *     public String delete(@PathVariable Integer id, HttpServletResponse servletResponse) throws IOException {
 *          //执行sql语句返回的受影响条数嘛
 *         int cout = service.deleted(id);//将id传给删除的方法，删除，删除成功，则表示删除了这1条数据，所以1赋给count，再判断cont==1.
 *         if (cout==1){
 *             servletResponse.sendRedirect("/note/noteList");
 *         }
 *         return "error";
 *     }
 */

/**
 *
 * 数据库增字段，创建新表用户登录

 create database Book;
 use Book;
 /*drop table NoteBook;
create table NoteBook(
            noteBookId int primary key AUTO_INCREMENT,
            noteBookName varchar(50) not null
            );
    insert into notebook(noteBookName) values('天覆吾，地载吾，天地生吾有意无');
    insert into notebook(noteBookName) values('不然绝粒升天衢，不然鸣珂游帝都');
    insert into notebook(noteBookName) values('焉能不贵复不去，空作昂藏一丈夫');

    select * from NoteBook;
    show databases like 'book';

    use Book;
    drop table Note;
    create table Note(
            id int primary key auto_increment,
    `name` varchar(50) not null,
    content varchar(200)
);
#在表中插入一个字段 alter table 表名 add 字段名 字段类型 not null default '0';
    alter table note add isDelete int(1) not null default '0';
#逻辑删除语句：删除时 update user set deleted=1 where id =1 and deleted=0查找时 select * from user where deleted=0
    update note set isDelete =1 where id = 23;
    select * from note where isDelete =0;
    select count(*) from note where isDelete = 0;
    insert into Note(`name`,content) values('天覆吾，地载吾，天地生吾有意无','记事1');
    insert into Note(`name`,content) values('不然绝粒升天衢，不然鸣珂游帝都','记事2');
    insert into Note(`name`,content) values('焉能不贵复不去，空作昂藏一丈夫','记事3');
    select * from Note;


    update note set name ='123456' ,content = '654321' where id =17;

    select * from note where isDelete =0 limit 0,10 ;
    select * from note;


创建用户登录表
    create table user(
            id int primary key auto_increment,
    `username` varchar(50) not null,
            `password` varchar(50) not null
            );
    insert into user(`username`,`password`)values('zhang','000000');
    insert into user(`username`,`password`)values('wang','000000');
    insert into user(`username`,`password`)values('li','000000');
    select * from user;

    select * from user where username = 'zhang' and password = '111';
 * */
}
/**
 * 新数据库  book
 *老表
        *create database Book;
        *use Book;
        *drop table NoteBook;
        *create table NoteBook(
        *noteBookId int primary key AUTO_INCREMENT,
        *noteBookName varchar(50)not null
        *);
        *insert into notebook(noteBookName)values('天覆吾，地载吾，天地生吾有意无');
        *insert into notebook(noteBookName)values('不然绝粒升天衢，不然鸣珂游帝都');
        *insert into notebook(noteBookName)values('焉能不贵复不去，空作昂藏一丈夫');
        *select*from NoteBook;
        *
        *  springboot项目表
        *show databases like'book';
        *create database Book;
        *use Book;
        *drop table Note;
 *create table Note(
 * 	id int primary key auto_increment,
 *     `name` varchar(50) not null,
 *     content varchar(200)
 * );
 * #在表中插入一个字段 alter table 表名 add 字段名 字段类型 not null default '0';
 * alter table note add isDelete int(1) not null default '0';
 * #逻辑删除语句：删除时 update user set deleted=1 where id =1 and deleted=0查找时 select * from user where deleted=0
 * update note set isDelete =1 where id = 23;
 * select * from note where isDelete =0;
 * select count(*) from note where isDelete = 0;
 * insert into Note(`name`,content,uId) values('天覆吾，地载吾，天地生吾有意无','记事1',1);
 * insert into Note(`name`,content,uId) values('不然绝粒升天衢，不然鸣珂游帝都','记事2',2);
 * insert into Note(`name`,content,uId) values('焉能不贵复不去，空作昂藏一丈夫','记事3',3);
 * update note set isDelete =1 where id= 35;
 * update note set isDelete =0;
 * update note set name ='123456' ,content = '654321' where id =17;
 * select * from note where isDelete =0 limit 0,10 ;
 * select * from note;
 *
 * create table user(
 * 	id int primary key auto_increment,
 * 	`username` varchar(50) not null,
 * 	`password` varchar(50) not null
 * );
 * insert into user(`username`,`password`)values('zhang','000000');
 * insert into user(`username`,`password`)values('wang','000000');
 * insert into user(`username`,`password`)values('li','000000');
 * select * from user;
 * select * from user where username = 'zhang' and password = '111';
 * /* 	set sql_safe_updates=0  关闭安全模式
 * 	set_sql_safe_updates=1  开启安全模式
 *
 *note表添加外键
        *delete from note;/*删除表中所有数据
        *alter table note modify column uId int not null;/*将外键设置为not null
        *alter table note add uId int;/*在note表中新增一个uId字段
        *alter table note add constraint noteUserfk foreign key(uId)references user(id);/*将外键与主表中的键进行关联
        *
        * /*解除外键约束——去掉表和表之间的链接
        *alter table note drop foreign key noteUserfk;
        * /*
 * 	建表时增加外键
 * 		mysql> create table fk_test(
 * 			>	course_id int auto_increment primary key,
 * 			>	course_name carchar(30),
 * 			>	id int,
 * 			>	constraint FK_1 foreign key(id) references test(id)
 * 			>);
 * 	建表完成过后，通过sql语句增加外键
 * 		alter table table-name add constraint 外键名 foreign key(字段名) references 引用表明(字段名)
 * 	删除主外键关联表（下列语句不对）
 * 		alter table table-name add constraint 外键名 foreign key(字段名) references 引用表明(字段名) on delete cascade
 *
 *
 *     表名为 orderitem
 * 		CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)//建立外键
 * 		alter table orderitem drop foreign key orderitem_ibfk_1;//删除外键关联
 *         alter table 表名 drop foreign key 外键名;
 *
        *show create table note;
        *
        *select*from note;
        *select*from user;
        *
        * /*链接查询与内外键的关系：
 * 	连接的 连接条件：A表的主键 = B表的外键如果做连接的两表没有主外键关系，那么连接结果没有意义。
 * 	例：学生表和选课表做连接，select *from 学生表,选课表where 学生表.学号（主键）=选课表.学号（外键）
 * 	主外键可以建级联级触发器，用来做级联删除、更新。作用原理还是因为主外键将两表联系。
 *
        *select*from user,note where user.id=note.uId;
        * #那回到记事本那边那个问题，一个用户没有记事，体现在数据库是什么样的
        *select note.*from note left join user on note.uId=user.id where name='3';
        *select note.*from note left join user on note.uId=user.id;
        *
        * #写个句子查询每个房间住了几个人
        *select*from note left join user on note.uId=user.id;
        *
        *select count(*)from note;
        *
        *select count(*)from user;
        * #查询某个用户有多少条数据，user id作为判定条件
        *select note.*from note left join user on note.uId=user.id where user.id=3;
        *select*from note where uId=3;#不就可以了
        * #select*from note where uId= #{id} ，不就可以了
        *
        * #select*from note where isDelete=0 limit #{pageNumber},#{pageSize}
        *
        *select*from note where isDelete=0 limit 0,10;
        * #查询当前用户的数据，并按每页10条显示
        *select note.*from note left join user on note.uId=user.id where user.id=3 and isDelete=0 limit 0,10;
 *
 * */