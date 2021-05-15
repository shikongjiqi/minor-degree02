package edu.huc.service;

import edu.huc.bean.User;
import edu.huc.common.vo.UserInfoVo;
import edu.huc.common.vo.UserVo;
import edu.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    UserVo login(String username, String password);

    RespData register(User user);

    RespData delUser(int userId);

    RespData adminQueryUser(int page);

    RespData adminUpdateUser(User user);

    RespData adminSelectById(int userId);

    void deleteUserMessage(int userId);

    UserVo getById(int userId);

    UserVo convertUser(User user);

    UserInfoVo queryUserInfo(int userId);

    UserInfoVo editPassword(String newPassword,int userId);
}
