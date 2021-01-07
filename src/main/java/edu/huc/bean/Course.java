package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_course")
public class Course {
    @TableId(value = "course_id",type = IdType.AUTO)
    private Integer courseId;

    private Integer minorId;

    private String courseName;

    private Integer teacherId;

    private String timeTable;

    private int reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
}