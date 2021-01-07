package edu.huc.controller;

import edu.huc.bean.Course;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.service.ICourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CourseController {
    @Resource
    private ICourseService courseService;

    /**
     * 查询自己的辅修专业的课程
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("queryMyCourse")
    public String queryMyMinorCourse(Model model, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        //查询前先判定用户是否登录，只有在登录状态下才可以查看
        if (userId == null || userId == 0){//判定用户是否登录
            RespData respData = new RespData(RespCode.ERROR_SESSION,null);
            model.addAttribute("respData",respData);
            return "success";
        }else{
            //去数据库中查询数据
            RespData respData = courseService.queryMyCourse(userId);
            model.addAttribute("respData",respData);
            model.addAttribute("data", (List<Course>) respData.getData());
            if (respData.getCode() != 0){
                model.addAttribute("respData",respData);
                return "success";
            }else {
                model.addAttribute("data", (List<Course>) respData.getData());
                return "course_my";
            }
        }
    }

    /**
     * 查询教师相关课程
     * @param session
     * @return
     */
    @RequestMapping("queryTeacherCourse")
    @ResponseBody
    public RespData queryTeacherCourse(HttpSession session){
        Integer id = (Integer)session.getAttribute("userId");
        if (id == null || id == 0)//检验用户是否登录
            return new RespData(RespCode.ERROR_SESSION);
        RespData respData = courseService.queryTeacherCourse(id);
        return respData;
    }

    /**
     * 查询与该辅修学位相关课程
     * @param minorId
     * @return
     */
    @PostMapping("queryAboutCourse")
    @ResponseBody
    public RespData queryAboutCourse(int minorId){
        RespData respData = courseService.queryAboutCourse(minorId);
        return respData;
    }
}
