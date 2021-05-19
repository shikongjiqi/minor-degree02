package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_board")
public class Board {
    @TableId(value = "board_id",type = IdType.AUTO)
    private int boardId;

    private String title;

    private String callBoard;

    private int minorId;

    private int crowd;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
}
