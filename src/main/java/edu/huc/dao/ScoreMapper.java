package edu.huc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.huc.bean.Score;
import edu.huc.common.vo.ScoreVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreMapper extends BaseMapper<Score> {
    @Select({"select username,name,minor_name from tb_score s,tb_course c where c.course_id = s.course_id and teacher_id = #{teacherId}"})
    List<Score> queryMyStudent(int teacherId);

    @Select("select s.username,s.name,s.score,c.course_name from tb_score s,tb_course c where c.course_id=s.course_id and teacher_id=#{teacherId}")
    List<ScoreVo> queryMyCourse(int teacherId);

    @Select({"select * from tb_score where minor_name = #{minorName}"})
    List<Score> queryScore(String minorName);

    @Select({"select * from tb_score where username = #{userName}"})
    List<Score> queryMyScore(String userName);
}
