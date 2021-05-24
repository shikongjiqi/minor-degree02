package edu.huc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.huc.bean.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper extends BaseMapper<Board> {
    @Select({"select * from tb_board"})
    List<Board> queryBoard();

    @Select({"insert into tb_board (title,call_board) values(title,callBoard)"})
    void insertBoard(String title,String callBoard);
}
