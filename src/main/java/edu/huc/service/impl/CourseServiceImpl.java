package edu.huc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.huc.bean.Course;
import edu.huc.bean.EntryForm;
import edu.huc.bean.Teacher;
import edu.huc.bean.User;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.vo.CourseVo;
import edu.huc.dao.CourseMapper;
import edu.huc.dao.EntryFormMapper;
import edu.huc.dao.TeacherMapper;
import edu.huc.dao.UserMapper;
import edu.huc.service.ICourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private EntryFormMapper entryFormMapper;
    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public RespData queryMyCourse(int userId) {
        User user = userMapper.selectById(userId);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userName",user.getUsername());
        EntryForm entryForm = entryFormMapper.selectOne(queryWrapper);
        if (entryForm == null){
            return new RespData(RespCode.ENPTY);
        }
        if (entryForm.getChecked() == 1){//报名信息没有审核
            return new RespData(RespCode.WAIT_CHECK);
        }
        List<Course> courseList = courseMapper.queryMyCourse(entryForm.getMinorId());
        if (courseList.isEmpty()){
            //成功查询，但是数据为空
            return new RespData(RespCode.SUCCESS,null);
        }
        return new RespData(RespCode.SUCCESS,courseList);
    }

    @Override
    public RespData queryTeacherCourse(int id) {
        Teacher teacher = teacherMapper.selectById(id);
        if (teacher == null)
            return new RespData(RespCode.WRONG);
        List<Course> courses = courseMapper.queryTeacherCourse(teacher.getTeacherId());
        if (courses == null)
            return new RespData(RespCode.SUCCESS,null);
        if (courses.isEmpty())
            return new RespData(RespCode.SUCCESS,null);
        return new RespData(RespCode.SUCCESS,courses);
    }

    @Override
    public RespData queryAboutCourse(int minorId) {
        List<Course> courseList = courseMapper.queryMyCourse(minorId);
        if (courseList.isEmpty()){
            return new RespData(RespCode.WRONG);
        }
        List<CourseVo> list = convertList(courseList);
        return new RespData(RespCode.SUCCESS,list);
    }

    private List<CourseVo> convertList(List<Course> list){
        List<CourseVo> courseList = new ArrayList<>();
        for (Course c: list) {
            CourseVo course = new CourseVo();
            course.setCourseId(c.getCourseId());
            course.setCourseName(c.getCourseName());
            course.setTimeTable(c.getTimeTable());
            Teacher teacher = teacherMapper.selectById(c.getTeacherId());
            course.setTeacherName(teacher.getUserName());
            courseList.add(course);
        }
        return courseList;
    }
}
