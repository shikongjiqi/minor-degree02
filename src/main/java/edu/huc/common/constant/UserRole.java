package edu.huc.common.constant;

public enum  UserRole {
    ADMIN(0,"管理员"),
    TEACHER(1,"教师"),
    STUDENT(2,"学生"),
    TOURIST(3,"院长"),
    SUPER_ADMIN(4,"超级管理员");

    private Integer code;
    private String name;


    UserRole(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
