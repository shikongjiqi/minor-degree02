package edu.huc.controller;

import edu.huc.common.response.RespData;
import edu.huc.service.IAcademyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/academy")
public class AcademyController {
    @Resource
    private IAcademyService academyService;

    @RequestMapping("/queryMyInfo")
    public RespData queryMyInfo(int userId){
        return null;
    }
}
