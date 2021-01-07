package edu.huc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.huc.bean.*;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.result.ResultScore;
import edu.huc.dao.*;
import edu.huc.service.IScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements IScoreService {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private EntryFormMapper entryFormMapper;
    @Resource
    private MinorMapper minorMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public RespData queryMyStudent(int userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userId",userId);
        Teacher teacher = teacherMapper.selectOne(queryWrapper);
        List<Score> scoreList = scoreMapper.queryMyStudent(teacher.getTeacherId());
        if (scoreList.isEmpty()){
            return new RespData(RespCode.SUCCESS,null);
        }
        return new RespData(RespCode.SUCCESS,scoreList);
    }

    @Override
    public RespData queryScore(int id) {
        //查询有疑问
        //不记得查询条件
        EntryForm entryForm = entryFormMapper.selectById(id);
        if (entryForm == null)
            return new RespData(RespCode.ENPTY);
        Minor minor = minorMapper.selectById(entryForm.getMinorId());
        List<Score> scores = scoreMapper.queryScore(minor.getName());
        if (scores.isEmpty())
            return new RespData(RespCode.WRONG);
        List<ResultScore> scoreList = convertScoreList(scores);
        return new RespData(RespCode.SUCCESS,scoreList);
    }

    private List<ResultScore> convertScoreList(List<Score> scoreList){
        List<ResultScore> list = new ArrayList();
        for (int i = 0;i < scoreList.size();i ++){
            ResultScore resultScore = new ResultScore();
            Score score = scoreList.get(i);
            resultScore.setUserName(score.getUsername());
            resultScore.setName(score.getName());
            Course course = courseMapper.queryCourseName(score.getCourseId());
            resultScore.setCourseName(course.getCourseName());
            resultScore.setMinorName(score.getMinorName());
            resultScore.setScore(score.getScore());
            list.add(resultScore);
        }
        return list;
    }

    @Override
    public RespData queryMyScore(int userId) {
        User user = userMapper.selectById(userId);
        List<Score> scores = scoreMapper.queryMyScore(user.getUsername());
        if (scores.isEmpty())
            return new RespData(RespCode.WRONG);
        List<ResultScore> scoreList = convertScoreList(scores);
        return new RespData(RespCode.SUCCESS,scoreList);
    }
}
