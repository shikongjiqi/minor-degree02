package edu.huc.common.vo;

import edu.huc.common.constant.UserRole;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private int id;

    private String username;

    private String name;

    private Integer role;

    private String identity;

    public UserVo(){}

    public UserVo(UserRole userRole){
        this.role = userRole.getCode();
        this.identity = userRole.getName();
    }
}
