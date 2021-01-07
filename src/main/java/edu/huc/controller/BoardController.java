package edu.huc.controller;

import edu.huc.common.response.RespData;
import edu.huc.service.IBoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BoardController {
    @Resource
    private IBoardService boardService;

    /**
     * 查询公告
     * @return
     */
    @PostMapping("board")
    public RespData board(){
        RespData respData = boardService.queryBoard();
        return respData;
    }
}
