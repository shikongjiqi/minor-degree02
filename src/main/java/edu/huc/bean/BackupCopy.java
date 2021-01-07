package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_backup_copy")
public class BackupCopy {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String password;

    private String name;

    private String sex;

    private Integer age;

    private String phones;

    private String email;

    private String identity;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
}