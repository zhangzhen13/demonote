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
 */
}