package edu.huc.service;

import edu.huc.bean.User;
import edu.huc.common.response.RespData;
import edu.huc.common.vo.ResultUser;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    ResultUser login(String username, String password);

    RespData register(User user);

    RespData delUser(int userId);

    RespData adminQueryUser(int page);

    RespData adminUpdateUser(User user);

    RespData adminSelectById(int userId);

    void deleteUserMessage(int userId);

    User getById(int userId);

    ResultUser convertUser(User user);
}
