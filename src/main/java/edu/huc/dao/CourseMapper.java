package edu.huc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.huc.bean.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    @Insert({"insert into tb_course (course_id, minor_id, course_name, teacher_id, time_table, reserved1, reserved2, reserved3, reserved4 values (default, " +
            "#{minorId,jdbcType=INTEGER}, #{courseName,jdbcType=VARCHAR}, \n" +
            "      #{teacherId,jdbcType=VARCHAR}, #{timeTable,jdbcType=VARCHAR}, #{reserved1,jdbcType=VARCHAR}, \n" +
            "      #{reserved2,jdbcType=VARCHAR}, #{reserved3,jdbcType=VARCHAR}, #{reserved4,jdbcType=VARCHAR}"})
    int insert(Course record);

    @Select({"select * from tb_course where minor_id = #{minorId}"})
    List<Course> queryMyCourse(int minorId);

    //根据教师id查询老师课程
    @Select({"select * from tb_course where teacher_id = #{id}"})
    List<Course> queryTeacherCourse(int id);

    @Select({"select * from tb_course where minor_id = #{minorId}"})

    List<Course> queryMinorCourse(int minorId);
    @Select({"select course_name from tb_course where course_id = #{courseId}"})
    Course queryCourseName(int courseId);
}