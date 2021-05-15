package edu.huc.common.vo;

import lombok.Data;

@Data
public class PasswordVo {
    private  String password;
    private String newPassword;
    private int userId;
}
