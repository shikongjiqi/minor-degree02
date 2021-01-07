package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_minor")
public class Minor {
    @TableId(value = "minor_id",type = IdType.AUTO)
    private Integer minorId;

    private String name;

    private Integer academyId;

    private Integer count;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
}