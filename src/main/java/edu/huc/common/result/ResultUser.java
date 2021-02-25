package edu.huc.common.result;

import edu.huc.common.constant.UserRole;

/**
 * 登录--对用户信息数据的返回信息进行封装
 */
public class ResultUser {
    private int userId;

    private String userName;

    private String name;

    private Integer role;

    private String identity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public ResultUser(UserRole userRole){
        this.role = userRole.getCode();
        this.identity = userRole.getName();
    }
}
