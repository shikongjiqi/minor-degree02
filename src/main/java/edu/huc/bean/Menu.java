package edu.huc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_menu")
public class Menu {
    @TableId(value = "menu_id",type = IdType.AUTO)
    private Integer menuId;

    private String menuName;

    private String path;

    private Integer orderNum;

    private Integer role;
}
