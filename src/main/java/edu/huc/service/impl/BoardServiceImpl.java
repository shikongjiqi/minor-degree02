package edu.huc.service.impl;

import edu.huc.bean.Board;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.dao.BoardMapper;
import edu.huc.service.IBoardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class BoardServiceImpl implements IBoardService {
    @Resource
    private BoardMapper boardMapper;

    //查询通知
    @Override
    public RespData queryBoard() {
        List<Board> boardList = boardMapper.queryBoard();
        if (boardList.isEmpty()){
            return new RespData(RespCode.SUCCESS,null);
        }
        return new RespData(RespCode.SUCCESS,boardList);
    }
}
