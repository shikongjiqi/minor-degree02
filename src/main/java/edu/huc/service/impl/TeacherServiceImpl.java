package edu.huc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.huc.bean.Teacher;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.result.ResultTeacher;
import edu.huc.dao.TeacherMapper;
import edu.huc.service.ITeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    //管理员查询教师资料
    @Override
    public RespData queryTeacher() {
        List<Teacher> teacherList = teacherMapper.selectList(null);
        return new RespData(RespCode.SUCCESS,teacherList);
    }

    /**
     * 管理员修改老师数据之前查询出数据，为前端input框里提供原始数据
     * @param teacherId
     * @return
     */
    @Override
    public RespData selectTeacherById(int teacherId) {
        Teacher teacher = teacherMapper.selectById(teacherId);
        ResultTeacher resultTeacher = ResultTeacher(teacher);
        return new RespData(RespCode.SUCCESS,resultTeacher);
    }

    /**
     * 修改老师数据
     * @param teacher
     */
    @Override
    public void adminUpdateTeacherMessage(Teacher teacher) {
        teacherMapper.updateById(teacher);
    }

    /**
     * 删除教师信息
     * @param userId
     */
    @Override
    public void deleteTeacherMessage(int userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userId",userId);
        teacherMapper.delete(queryWrapper);
    }

    private ResultTeacher ResultTeacher(Teacher teacher){
        ResultTeacher teachers = new ResultTeacher();
        teachers.setTeacherId(teacher.getTeacherId());
        teachers.setTeacherProfessional(teacher.getProfessional());
        teachers.setTeacherName(teacher.getUserName());
        teachers.setAcademyName(teacher.getAcademyName());
        return teachers;
    }
}
