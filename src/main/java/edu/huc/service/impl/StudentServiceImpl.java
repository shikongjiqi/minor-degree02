package edu.huc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.huc.bean.Student;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.vo.StudentVo;
import edu.huc.dao.StudentMapper;
import edu.huc.service.IStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements IStudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public RespData queryStudent(int page) {
        Page selectPage = studentMapper.selectPage(new Page(page, 10), null);
        if (selectPage.getRecords().isEmpty())
            return new RespData(RespCode.USER_LOCKED);
        return new RespData(RespCode.SUCCESS,selectPage);
    }

    /**
     * 管理员修改学生数据之前实现的查询数据，向前端input标签里提供数据
     * @param studentId
     * @return
     */
    @Override
    public RespData selectMessage(int studentId) {
        Student student = studentMapper.selectById(studentId);
        StudentVo resultStudent = ResultStudent(student);
        return new RespData(RespCode.SUCCESS,resultStudent);
    }

    /**
     * 管理员修改学生数据
     * @param student
     */
    @Override
    public void adminUpdateMessage(Student student) {
        QueryWrapper queryWrapper = new QueryWrapper();
        studentMapper.updateById(student);
    }

    /**
     * 删除学生信息
     * @param studentId
     */
    @Override
    public void deleteStudentMessage(int studentId) {
        studentMapper.deleteById(studentId);
    }

    private StudentVo ResultStudent(Student student){
        StudentVo resultStudent = new StudentVo();
        resultStudent.setStudentId(student.getStudentId());
        resultStudent.setStudentName(student.getUserName());
        resultStudent.setClasses(student.getClasses());
        resultStudent.setMajorName(student.getMajorName());
        resultStudent.setAcademyName(student.getAcademyName());
        return resultStudent;
    }
}
