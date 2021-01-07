package edu.huc.service.impl;

import edu.huc.bean.Academy;
import edu.huc.bean.Minor;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.result.ResultMinor;
import edu.huc.dao.AcademyMapper;
import edu.huc.dao.MinorMapper;
import edu.huc.service.IMinorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MinorServiceImpl implements IMinorService {
    @Resource
    private MinorMapper minorMapper;
    @Resource
    private AcademyMapper academyMapper;

    @Override
    public RespData queryMinor(int page) {
//        PageHelper.startPage(page, 3);//分页处理
//        List<Minor> listMinor = minorMapper.queryMinor();//查询辅修数据
//        List<ResultMinor> list = convertMinorList(listMinor);//将查询数据转换为我们所需要的字段
//        PageInfo<ResultMinor> pageInfo = new PageInfo<ResultMinor>(list);
        return new RespData(RespCode.SUCCESS);
    }

    //数据转换，将辅修课程转换为所需数据
    private List<ResultMinor> convertMinorList(List<Minor> minorList){
        List<ResultMinor> list = new ArrayList<ResultMinor>();
        for (Minor minor : minorList) {
            Academy academy = academyMapper.selectById(minor.getAcademyId());//将辅修课程数据转换为前端需要的字段
            ResultMinor resultMinor = convertMinor(minor.getMinorId(), minor.getName(), academy.getAcademyName(),  minor.getCount());
            list.add(resultMinor);
        }
        return list;
    }

    //数据转换封装
    private ResultMinor convertMinor(int minorId,String name,String academyName,int count){
        ResultMinor resultMinor = new ResultMinor();
        resultMinor.setMinorId(minorId);
        resultMinor.setMinorName(name);
        resultMinor.setAcademy(academyName);
        resultMinor.setCount(count);
        return resultMinor;
    }
}
