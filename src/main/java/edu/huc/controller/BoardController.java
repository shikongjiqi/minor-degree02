package edu.huc.controller;

import edu.huc.bean.Board;
import edu.huc.common.response.RespCode;
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
     *
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/getBoard")
    public RespData board(@RequestParam(value = "page",defaultValue = "1") int page) {
        System.out.println(page);
        RespData respData = boardService.queryBoard(page);
        return respData;
    }

    @RequiresAuthentication
    @PostMapping("/insertBoard")
    public RespData insertBoard(@RequestBody Board board){
        int insert = boardService.insert(board);
        return new RespData(RespCode.SUCCESS,insert);
    }

    public void insertBoard(String title,String callBoard){
        boardService.insertBoard(title,callBoard);

    }
}
