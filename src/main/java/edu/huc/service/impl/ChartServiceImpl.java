package edu.huc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.huc.bean.Academy;
import edu.huc.bean.Major;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.result.ResultHistogramData;
import edu.huc.common.result.ResultPieData;
import edu.huc.dao.AcademyMapper;
import edu.huc.dao.EntryFormMapper;
import edu.huc.dao.MajorMapper;
import edu.huc.service.IChartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ChartServiceImpl implements IChartService {
    @Resource
    private AcademyMapper academyMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private EntryFormMapper entryFormMapper;

    //生成饼图
    @Override
    public RespData statics(int minorId) {
        List<Academy> academyList = academyMapper.selectList(null);
        List<ResultPieData> list = new ArrayList<>();
        for (int i = 0;i < academyList.size();i++){
            ResultPieData resultPieData = new ResultPieData();
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("academy_id",academyList.get(i).getAcademyId());
            List<Major> majors = majorMapper.selectList(queryWrapper);
            int integer = 0;
            for (int j = 0;j < majors.size();j ++){
                integer += entryFormMapper.selectCountEntryForm(majors.get(j).getMajorName());
            }
            resultPieData.setName(academyList.get(i).getAcademyName());
            resultPieData.setValue(integer);
            list.add(resultPieData);
        }
        return new RespData(RespCode.SUCCESS,list);
    }

    //生成柱状图
    @Override
    public RespData histogram(int minorId, String academyName) {
        ResultHistogramData resultHistogramData = new ResultHistogramData();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("academy_name",academyName);
        Academy academy = academyMapper.selectOne(queryWrapper);
        queryWrapper.clear();
        queryWrapper.eq("academy_id",academy.getAcademyId());
        List<Major> majorList = majorMapper.selectList(queryWrapper);
        String[] x = new String[majorList.size()];
        int[] y = new int[majorList.size()];
        for (int i = 0;i < majorList.size();i ++){
            x[i] = majorList.get(i).getMajorName();
            queryWrapper.clear();
            queryWrapper.eq("major_name",majorList.get(i).getMajorName());
            queryWrapper.eq("minor_id",minorId);
            Integer integer = entryFormMapper.selectCount(queryWrapper);
            y[i] = integer;
        }
        resultHistogramData.setDataX(x);
        resultHistogramData.setDataY(y);
        return new RespData(RespCode.SUCCESS,resultHistogramData);
    }
}
