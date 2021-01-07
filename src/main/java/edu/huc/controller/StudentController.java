package edu.huc.controller;

import edu.huc.bean.Student;
import edu.huc.common.response.RespData;
import edu.huc.common.result.ResultStudent;
import edu.huc.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class StudentController {
    @Resource
    private IStudentService studentService;

    /**
     * 查询全部学生
     * @return
     */
    @RequestMapping("admin/queryStudent")
    @ResponseBody
    public RespData queryStudent(@RequestParam(value = "page",defaultValue = "1")int page){
        RespData respData = studentService.queryStudent(page);
        return respData;
    }

    @RequestMapping("admin/selectStudent")
    public String selectStudent(int studentId, Model model){
        RespData respData = studentService.selectMessage(studentId);
        model.addAttribute("student",(ResultStudent)respData.getData());
        return "admin/update_student";
    }

    @RequestMapping("admin/updateStudent")
    public String updateStudent(int student_id,String user_name,String classes,String major_name,String academy_name){
        Student student = new Student();
        student.setStudentId(student_id);
        student.setUserName(user_name);
        student.setClasses(classes);
        student.setMajorName(major_name);
        student.setAcademyName(academy_name);
        studentService.adminUpdateMessage(student);
        return "admin/success";
    }

    @RequestMapping("admin/deleteStudent")
    public String deleteStudent(int studentId){
        studentService.deleteStudentMessage(studentId);
        return "admin/success";
    }
}
