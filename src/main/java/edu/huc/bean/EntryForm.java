package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_entry_form")
public class EntryForm {
    @TableId(value = "entry_form_id",type = IdType.AUTO)
    private Integer entryFormId;

    private String userName;

    private String majorName;

    private String cardId;

    private Float averageScore;

    private String interestCourse;

    private Integer minorId;

    private Integer checked;

    private String reserved1;//存储图片名-回执单

    private String reserved2;

    private String reserved3;

    private String reserved4;
}