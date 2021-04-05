package edu.huc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.huc.bean.Teacher;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.vo.TeacherVo;
import edu.huc.dao.TeacherMapper;
import edu.huc.service.ITeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    //管理员查询教师资料
    @Override
    public RespData queryTeacher(int page) {
        Page<Teacher> teacherPage = teacherMapper.selectPage(new Page<>(page, 10), null);
        return new RespData(RespCode.SUCCESS,teacherPage);
    }

    /**
     * 管理员修改老师数据之前查询出数据，为前端input框里提供原始数据
     * @param teacherId
     * @return
     */
    @Override
    public RespData selectTeacherById(int teacherId) {
        Teacher teacher = teacherMapper.selectById(teacherId);
        TeacherVo resultTeacher = ResultTeacher(teacher);
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

    private TeacherVo ResultTeacher(Teacher teacher){
        TeacherVo teachers = new TeacherVo();
        teachers.setTeacherId(teacher.getTeacherId());
        teachers.setTeacherProfessional(teacher.getProfessional());
        teachers.setTeacherName(teacher.getUserName());
        teachers.setAcademyName(teacher.getAcademyName());
        return teachers;
    }
}
