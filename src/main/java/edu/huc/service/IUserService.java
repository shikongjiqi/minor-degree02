package edu.huc.service;

import edu.huc.bean.User;
import edu.huc.common.response.RespData;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface IUserService {
    RespData login(String username, String password);

    RespData register(String username, String password, String name, String sex, String card_id, int age, String phones, String email, String identity, Model model);

    RespData delUser(int userId);

    RespData adminQueryUser(int page);

    RespData adminUpdateUser(User user);

    RespData adminSelectById(int userId);

    void deleteUserMessage(int userId);
}
