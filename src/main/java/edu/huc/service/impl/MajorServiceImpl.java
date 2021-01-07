package edu.huc.service.impl;

import edu.huc.dao.MajorMapper;
import edu.huc.service.IMajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MajorServiceImpl implements IMajorService {
    @Resource
    private MajorMapper majorMapper;

//    @Override
//    public Major selectMajorId(String majorName) {
//       Major major = majorMapper.selectIdByMajorName(majorName);//查询主专业,仅查询id
//       return major;
//    }
}
