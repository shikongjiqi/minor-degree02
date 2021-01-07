package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_board")
public class Board {
    @TableId(value = "board_id",type = IdType.AUTO)
    private int boardId;

    private String title;

    private String callBoard;

    private int minorId;

    private int crowd;

    private String time;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
}
