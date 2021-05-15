package edu.huc.controller;

import edu.huc.common.response.RespData;
import edu.huc.service.ICourseService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequiresAuthentication
@RequestMapping("/course")
public class CourseController {
    @Resource
    private ICourseService courseService;

    /**
     * 查询自己的辅修专业的课程
     *
     * @return
     */
    @GetMapping("/queryMyCourse")
    public RespData queryMyMinorCourse(int userId) {
        //去数据库中查询数据
        RespData respData = courseService.queryMyCourse(userId);
        return respData;
    }

    /**
     * 查询教师相关课程
     *
     * @return
     */
    @GetMapping("/queryTeacherCourse")
    public RespData queryTeacherCourse(int userId) {
        RespData respData = courseService.queryTeacherCourse(userId);
        return respData;
    }

    /**
     * 查询与该辅修学位相关课程
     *
     * @param minorId
     * @return
     */
    @GetMapping("/queryAboutCourse")
    public RespData queryAboutCourse(@RequestParam String minorId) {
        Integer id = Integer.parseInt(minorId);
        RespData respData = courseService.queryAboutCourse(id);
        return respData;
    }
}
