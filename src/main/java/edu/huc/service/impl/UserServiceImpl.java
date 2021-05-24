package edu.huc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.huc.bean.User;
import edu.huc.common.constant.UserRole;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.vo.AdminUserVo;
import edu.huc.common.vo.UserInfoVo;
import edu.huc.common.vo.UserVo;
import edu.huc.dao.UserMapper;
import edu.huc.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public UserVo login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username).eq("password",password);
        User user = userMapper.selectOne(queryWrapper);//根据用户名、密码 查询
        if (user == null){
            return null;
        }
        UserVo result = convertUser(user);
        return result;
    }

    //将查询出的user信息转为前端所需的字段
    public UserVo convertUser(User user){
        UserVo result = null;
        if ("学生".equals(user.getIdentity())){
            result = new UserVo(UserRole.STUDENT);
        }
        if ("老师".equals(user.getIdentity())){
            result = new UserVo(UserRole.TEACHER);
        }
        if ("admin".equals(user.getIdentity())){
            result = new UserVo(UserRole.ADMIN);
        }
        if ("academy".equals(user.getIdentity())){
            result = new UserVo(UserRole.TOURIST);
        }
        if ("superadmin".equals(user.getIdentity())){
            result = new UserVo(UserRole.SUPER_ADMIN);
        }
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        result.setName(user.getName());
        return result;
    }

    @Override
    public UserInfoVo queryUserInfo(int userId) {
        UserInfoVo user = userMapper.selectUserInfoById(userId);
        return user;
    }

    @Override
    public UserInfoVo editPassword(String newPassword,int userId) {
        userMapper.editPassword(newPassword, userId);
        UserInfoVo user = userMapper.selectUserInfoById(userId);
        return user;
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
        AdminUserVo resultAdminUser = requestUser(user);
        return new RespData(RespCode.SUCCESS,resultAdminUser);
    }

    @Override
    public void deleteUserMessage(int userId) {
        userMapper.deleteById(userId);
    }

    @Override
    public UserVo getById(int userId) {
        User user = userMapper.selectById(userId);
        UserVo userVo = convertUser(user);
        return userVo;
    }

    //  数据封装
    private AdminUserVo requestUser(User user){
        AdminUserVo result = new AdminUserVo();
        result.setUserId(user.getId());
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setName(user.getName());
        result.setSex(user.getSex());
        result.setAge(user.getAge());
        result.setPhones(user.getPhones());
        result.setEmail(user.getEmail());
        result.setCardId(user.getCardId());
        result.setIdentity(user.getIdentity());
        return result;
    }
}
