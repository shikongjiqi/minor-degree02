package edu.huc.service.impl;

import edu.huc.dao.AcademyMapper;
import edu.huc.service.IAcademyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AcademyServiceImpl implements IAcademyService {
    @Resource
    private AcademyMapper academyMapper;
}
