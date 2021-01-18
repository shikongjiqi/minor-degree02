package edu.huc.controller;

import edu.huc.bean.Teacher;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.result.ResultTeacher;
import edu.huc.service.ITeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private ITeacherService teacherService;

    //以json数据格式返回
    @GetMapping("/queryTeacher")
    public RespData queryTeacher(){
        RespData respData = teacherService.queryTeacher();
        return respData;
    }

    @GetMapping("/selectTeacher")
    public RespData selectTeacher(int teacherId){
        RespData respData = teacherService.selectTeacherById(teacherId);
        return respData;
    }

    @PostMapping("/updateTeacher")
    public RespData updateTeacher(@RequestBody Teacher teacher){
        teacherService.adminUpdateTeacherMessage(teacher);
        return new RespData(RespCode.SUCCESS);
    }

    @GetMapping("/deleteTeacher")
    public RespData deleteTeacherMessage(int teacherId){
        teacherService.deleteTeacherMessage(teacherId);
        return new RespData(RespCode.SUCCESS);
    }
}
