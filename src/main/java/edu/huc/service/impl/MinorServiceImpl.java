package edu.huc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.huc.bean.Academy;
import edu.huc.bean.Minor;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.vo.MinorVo;
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
        Page minorPage = minorMapper.selectPage(new Page<>(page, 10), null);//查询数据
        List<Minor> minorList = minorPage.getRecords();
        List<MinorVo> list = new ArrayList<>();
        for(int i = 0;i < minorList.size();i ++){
            Academy academy = academyMapper.selectById(minorList.get(i).getAcademyId());
            MinorVo resultMinor = convertMinor(minorList.get(i).getMinorId(),minorList.get(i).getName(), academy.getAcademyName(), minorList.get(i).getCount());
            list.add(resultMinor);
        }
        minorPage.setRecords(list);
        return new RespData(RespCode.SUCCESS,minorPage);
    }

    //数据转换封装
    private MinorVo convertMinor(int minorId,String name,String academyName,int count){
        MinorVo resultMinor = new MinorVo();
        resultMinor.setMinorId(minorId);
        resultMinor.setMinorName(name);
        resultMinor.setAcademy(academyName);
        resultMinor.setCount(count);
        return resultMinor;
    }
}
