package edu.huc.service;

import edu.huc.bean.Board;
import edu.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface IBoardService {
    RespData queryBoard(int page);

    int insert(Board board);

    void insertBoard(String title,String callBoard);
}
