package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_academy")
public class Academy {
    @TableId(value = "academy_id",type = IdType.AUTO)
    private Integer academyId;

    private String academyName;

    private String academyAddress;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
}