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
     * @param session
     * @return
     */
    @PostMapping("/queryMyStudent")
    public RespData queryMyStudent(HttpSession session){
        Integer id = (Integer) session.getAttribute("userId");
        if (id == null || id == 0){
            return new RespData(RespCode.ERROR_SESSION);
        }
        RespData respData = scoreService.queryMyStudent(id);
        return respData;
    }

    @GetMapping("/queryScore")
    public RespData queryScore(HttpSession session){
        Integer id  = (Integer) session.getAttribute("userId");
        if(id == null || id == 0){
            return new RespData(RespCode.ERROR_SESSION);
        }
        RespData respData = scoreService.queryScore(id);
        return respData;
    }

    @GetMapping("queryMyScore")
    public RespData queryMyScore(HttpSession session){
        Integer id  = (Integer) session.getAttribute("userId");
        if(id == null || id == 0){
            return new RespData(RespCode.ERROR_SESSION);
        }
        RespData respData = scoreService.queryMyScore(id);
        return respData;
    }
}
