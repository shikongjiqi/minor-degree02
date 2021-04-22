package edu.huc.controller;

import edu.huc.bean.Student;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.service.IStudentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequiresAuthentication
@RequestMapping("/student")
public class StudentController {
    @Resource
    private IStudentService studentService;

    /**
     * 查询全部学生
     *
     * @return
     */
    @GetMapping("/queryStudent")
    public RespData queryStudent(@RequestParam(value = "page", defaultValue = "1") int page) {
        RespData respData = studentService.queryStudent(page);
        return respData;
    }

    @GetMapping("/selectStudent")
    public RespData selectStudent(int studentId) {
        RespData respData = studentService.selectMessage(studentId);
        return respData;
    }

    @PostMapping("/updateStudent")
    public RespData updateStudent(@RequestBody Student student) {
        studentService.adminUpdateMessage(student);
        return new RespData(RespCode.SUCCESS);
    }

    @GetMapping("/deleteStudent")
    public RespData deleteStudent(int studentId) {
        studentService.deleteStudentMessage(studentId);
        return new RespData(RespCode.SUCCESS);
    }
}
