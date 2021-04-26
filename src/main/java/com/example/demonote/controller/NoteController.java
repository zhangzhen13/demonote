package com.example.demonote.controller;//controll控制器

import com.example.demonote.domain.note;
import com.example.demonote.service.noteService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("note")
public class NoteController {

    @Autowired
    private noteService service;

    /**
     * 分页方式【2】 http://localhost:8009/note/test?pageNumber=1&pageSize=
     * @param model
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("/noteList")
    public String userNoteList(Model model,
                                    @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                    @RequestParam(name = "pageSize" ,defaultValue = "10") int pageSize,
                        HttpSession session,HttpServletRequest request) {

//        List<note> userList = service.userList(userId);

        int count = service.getCount();//总条数
        //总页数
        int totalPage;  //总页数

        if (count % pageSize == 0) {
            totalPage = count / pageSize;//总页数 当总条数除以每页显示数量结果无余数时，则总页数 = 总条数/每页条数
        }else {
            totalPage = count / pageSize + 1;//总页数 当总条数除以每页显示数量有余数时，则总页数 = 总条数/每页数 +1.除不尽的基础上多一页
        }

//        int pages = 0;
//        for (int i = 1;i<=totalPage;i++){
//            pages = i;
//        }
        //判断页码最大值最小值，做一个限制
        if (pageNumber < 1 ){
            pageNumber = 1;
        }else if(pageNumber > totalPage){
            pageNumber = totalPage;//当pageNumber大于总页数，则限制页码最大值.
        }

//        pageNumber<1 ? 1:(pageNumber>totalPage? totalPage:1)

        if (pageNumber<1){
            pageNumber = 1;//当totalPage=0时，这里设置pageNumber=1，用于给startCurrentPage设置值。以免pageNumber=0，startCurrentPage=-10情况
        }
        int starCurrentPage = (pageNumber - 1)* pageSize;//从第几个数据开始

//        int userId = request.getSession().getAttribute("id");
//        List<note> notes =service.selectPage(starCurrentPage,pageSize);
//        notes =service.selectPage(starCurrentPage,pageSize);
        //获取登录controller中存储的id值
        int id = (int) request.getSession().getAttribute("id");
        List<note> notes = service.selectUserPage(id,starCurrentPage,pageSize);

//        model.addAttribute("pages",pages);
        model.addAttribute("list" ,notes);
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("totalPage",totalPage);

        return "index";
    }

    /**
     *
     * @param model
     * @return
        //进入项目，显示项目列表（无分页情况）
        @RequestMapping("test")
        public String hello(Model model){
            List<note> list = service.selectAllnote();
            model.addAttribute("list",list);
            return "index";
     */
//    //在index.html页面点击【新增】按钮，进入add.html页面。方式1：
//    @RequestMapping("addIndx1")
//    public String addIndx(Model model){
//        List<note> list = service.selectAllnote();
//        model.addAttribute("list",list);
//        return "add";
//    }

    /**
     * //在index.html页面点击【新增】按钮，进入add.html页面。方式2：
     * @return
     */
    @RequestMapping("addIndx")
    public String addIndx(){
        return "add";
    }


    //新增
//    @RequestMapping("addNote")
//    @ResponseBody
//    public void add(note note){
//        service.addNote(note);
//    }
    //新增方法返回值为String类型
    @RequestMapping("addNote")
   // @ResponseBody
    public String add(note note,HttpSession session,HttpServletRequest request){
        int id = (int)request.getSession().getAttribute("id");//获取session里面存储的id
        note.setUId(id);//将获取的id赋给note对象的外键id值
        service.addNote(note);//添加这个对象
        return "redirect:/note/noteList";
    }

    /**
     * //查找数据id
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/updatePage/{id}")
    public String updatePage(Model model,@PathVariable int id){
        note notes = service.get(id);
        model.addAttribute("notes",notes);
        return "modifi";
    }
    //修改查找数据的内容
    @RequestMapping("/update")
    public String updateNote(Model model, note note){

        service.update(note);
        System.out.println(note);
        return "redirect:/note/noteList";
    }
    //删除数据
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, HttpServletResponse servletResponse,HttpSession session,HttpServletRequest request ) throws IOException {
        int uId = (int)request.getSession().getAttribute("id");//获取session里面存储的id
        int cout = service.deleted(id,uId);
        if (cout==1){
            servletResponse.sendRedirect("/note/noteList");
        }
        return "error";
    }

    //根据id查找，数据，进入详情页
    @RequestMapping("/details/{id}")
    public String detailsPage(Model model,@PathVariable int id){
        note notes = service.get(id);
        model.addAttribute("notes",notes);
        return "details";
    }
}
