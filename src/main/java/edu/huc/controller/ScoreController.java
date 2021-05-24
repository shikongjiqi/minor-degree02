package edu.huc.controller;

import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.service.IScoreService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequiresAuthentication
@RequestMapping("/score")
public class ScoreController {
    @Resource
    private IScoreService scoreService;

    /**
     * 点名册
     *
     * @return
     */
    @PostMapping("/queryMyStudent")
    public RespData queryMyStudent(int userId) {
        RespData respData = scoreService.queryMyStudent(userId);
        return respData;
    }

    /**
     * 老师查询学生成绩
     * @param userId
     * @return
     */
    @GetMapping("/queryMyCourse")
    public RespData queryMyCourse(int userId){
        RespData respData = scoreService.queryMyCourse(userId);
        return respData;
    }

    @GetMapping("/queryScore")
    public RespData queryScore(int userId) {
        RespData respData = scoreService.queryScore(userId);
        return respData;
    }

    @GetMapping("/queryMyScore")
    public RespData queryMyScore(int userId) {
        RespData respData = scoreService.queryMyScore(userId);
        return respData;
    }
}
