package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_student")
public class Student {
    @TableId(value = "student_id",type = IdType.AUTO)
    private Integer studentId;

    private String userName;

    private String majorName;

    private String academyName;

    private String classes;

    private String reserved1;//存储userID

    private String reserved2;

    private String reserved3;

    private String reserved4;
}