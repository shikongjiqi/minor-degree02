package edu.huc.controller;

import edu.huc.common.response.RespData;
import edu.huc.common.result.ResultTeacher;
import edu.huc.service.ITeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TeacherController {
    @Resource
    private ITeacherService teacherService;

    //以json数据格式返回
    @RequestMapping("admin/queryTeacher")
    @ResponseBody
    public RespData queryTeacher(){
        RespData respData = teacherService.queryTeacher();
        return respData;
    }

    @RequestMapping("admin/selectTeacher")
    public String selectTeacher(int teacherId, Model model){
        RespData respData = teacherService.selectTeacherById(teacherId);
        model.addAttribute("teacher",(ResultTeacher)respData.getData());
        return "admin/update_teacher";
    }

    @RequestMapping("admin/updateTeacher")
    public String updateTeacher(int teacher_id,String user_name,String academy_name,String professional){
        teacherService.adminUpdateTeacherMessage(teacher_id,user_name,academy_name,professional);
        return "admin/success";
    }

    @RequestMapping("admin/deleteTeacher")
    public String deleteTeacherMessage(int teacherId){
        teacherService.deleteTeacherMessage(teacherId);
        return "admin/success";
    }
}
