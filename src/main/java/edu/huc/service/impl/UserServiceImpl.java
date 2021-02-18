package edu.huc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.huc.bean.User;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.result.ResultAdminUser;
import edu.huc.common.result.ResultPage;
import edu.huc.common.result.ResultUser;
import edu.huc.dao.UserMapper;
import edu.huc.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 登录--调用dao接口去数据库中查询相应的用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public RespData login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username).eq("password",password);
        User user = userMapper.selectOne(queryWrapper);//根据用户名、密码 查询
        if (user == null){
            return new RespData(RespCode.ERROR_USER);
        }
        ResultUser result = convertUser(user);
        return new RespData(RespCode.SUCCESS,result);
    }

    //将查询出的user信息转为前端所需的字段
    private ResultUser convertUser(User user){
        ResultUser result = new ResultUser();
        result.setUserId(user.getUserId());
        result.setUserName(user.getName());
        result.setIdentity(user.getIdentity());
        return result;
    }

    /**
     * 将封装好的user对象添加到数据库中
     * @param user
     * @return
     */
    @Override
    public RespData register(User user) {
        int insert = userMapper.insert(user);//向数据库中添加用户信息
        if (insert == 0){
            return new RespData(RespCode.WRONG);
        }
        return new RespData(RespCode.SUCCESS);
    }

    //删除账号信息，并返回响应对象对数据进行封装
    @Override
    public RespData delUser(int userId) {
        userMapper.deleteById(userId);//删除数据库中的用户数据
        return new RespData(RespCode.SUCCESS);
    }

    //管理员对于用户信息的查询
    @Override
    public RespData adminQueryUser(int page) {
        Page<User> userPage = userMapper.selectPage(new Page<>(page, 10), null);
        return new RespData(RespCode.SUCCESS,userPage);
    }

    //    管理员修改用户信息
    public RespData adminUpdateUser(User user) {
        userMapper.update(user,null);
        return new RespData(RespCode.SUCCESS);
    }
    //  管理员查询用户所要修改的用户的信息
    public RespData adminSelectById(int userId){
        User user = userMapper.selectById(userId);
        ResultAdminUser resultAdminUser = requestUser(user);
        return new RespData(RespCode.SUCCESS,resultAdminUser);
    }

    @Override
    public void deleteUserMessage(int userId) {
        userMapper.deleteById(userId);
    }

    //  数据封装
    private ResultAdminUser requestUser(User user){
        ResultAdminUser result = new ResultAdminUser();
        result.setUserId(user.getUserId());
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setName(user.getName());
        result.setSex(user.getSex());
        result.setAge(user.getAge());
        result.setPhones(user.getPhones());
        result.setEmail(user.getEmail());
        result.setIdentity(user.getIdentity());
        return result;
    }
}
