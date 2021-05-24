package edu.huc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.huc.bean.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    @Select("select teacher_id from tb_teacher where reserved1=#{userId}")
    int queryTeacher(int userId);
}