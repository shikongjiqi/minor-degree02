package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_teacher")
public class Teacher {
    @TableId(value = "teacher_id",type = IdType.AUTO)
    private Integer teacherId;

    private String userName;

    private String academyName;

    private String professional;

    private String reserved1;//对应于user表中的id

    private String reserved2;

    private String reserved3;

    private String reserved4;
}