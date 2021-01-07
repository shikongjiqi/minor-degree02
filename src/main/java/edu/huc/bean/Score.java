package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_score")
public class Score {
    @TableId(value = "score_id",type = IdType.AUTO)
    private int scoreId;

    private String username;

    private String name;

    private String minorName;

    private int courseId;

    private double score;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
}
