package edu.huc.controller;

import edu.huc.common.response.RespData;
import edu.huc.service.IBoardService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Resource
    private IBoardService boardService;

    /**
     * 查询公告
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/getBoard")
    public RespData board(@RequestParam(defaultValue = "1") int page){
        RespData respData = boardService.queryBoard(page);
        return respData;
    }
}
