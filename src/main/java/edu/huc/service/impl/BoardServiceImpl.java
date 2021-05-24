package edu.huc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.huc.bean.Board;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.dao.BoardMapper;
import edu.huc.service.IBoardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BoardServiceImpl implements IBoardService {
    @Resource
    private BoardMapper boardMapper;

    //查询通知
    @Override
    public RespData queryBoard(int page) {
        Page<Board> boardPage = boardMapper.selectPage(new Page<>(page, 10), null);
        System.out.println(boardPage.getRecords());
        if (boardPage.getRecords().isEmpty()){
            return new RespData(RespCode.SUCCESS,null);
        }
        return new RespData(RespCode.SUCCESS,boardPage);
    }

    @Override
    public int insert(Board board) {
        int insert = boardMapper.insert(board);
        return insert;
    }

    @Override
    public void insertBoard(String title, String callBoard) {
        boardMapper.insertBoard(title, callBoard);

    }
}
