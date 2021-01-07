package edu.huc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    //跳转到图表显示页面
    @RequestMapping("teacher_success")
    public String teacherSuccess(){
        return "teacher_success";
    }

    @RequestMapping("uploadTable")
    public String uploadTable(){
        return "upload_table";
    }

    @RequestMapping("score")
    public String score(){
        return "score";
    }
}
