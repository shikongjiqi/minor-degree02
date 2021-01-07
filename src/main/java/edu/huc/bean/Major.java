package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_major")
public class Major {
    @TableId(value = "major_id",type = IdType.AUTO)
    private Integer majorId;

    private String majorName;

    private String majorCourse;

    private Integer academyId;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
}