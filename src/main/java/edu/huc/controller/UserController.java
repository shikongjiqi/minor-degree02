package edu.huc.controller;

import edu.huc.bean.User;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.service.IUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public RespData login(String username, String password){
        RespData respData = userService.login(username, password);//根据账号、密码登录
        return respData;
    }

    /**
     * 注册,将数据封装为我们所需要的user对象
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public RespData register1(@RequestBody User user){
        RespData respData = userService.register(user);//向数据库中添加用户数据
        return respData;
    }

    //退出账号
    @GetMapping("/editUser")
    public RespData editUser(HttpSession session){
        //移除session中的数据
        session.removeAttribute("userId");
        session.removeAttribute("username");
        session.removeAttribute("role");
        //返回登录页
        return new RespData(RespCode.SUCCESS);
    }

    /**
     * 注销账号删除账号信息
     * @return
     */
    @GetMapping("/delUser")
    public RespData delUser(HttpSession session){
        int id = (Integer)session.getAttribute("userId");
        RespData respData = userService.delUser(id);
        return respData;
    }

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
    public RespData loginAdmin(String username,String password){
        RespData respData = userService.login(username, password);
        return respData;
    }

    /**
     * 退出管理员
     * @return
     */
    @GetMapping("/editAdmin")
    public RespData editAdmin(HttpSession session){
        session.removeAttribute("userId");
        session.removeAttribute("username");
        session.removeAttribute("role");
        return new RespData(RespCode.SUCCESS);
    }

    /**
     * 管理员查询用户信息
     * @param page
     * @return
     */
    @GetMapping(value = "/queryUser")
    public RespData adminQueryUser(@RequestParam(value = "page",defaultValue = "1") int page){
        RespData respData = userService.adminQueryUser(page);
        return respData;
    }

    /**
     * 管理员查询需要修改的用户的信息
     * @param
     * @return
     */
    @GetMapping("/updateUser")
    public RespData updateUser(int userId,Model model){
        RespData respData = userService.adminSelectById(userId);
        return respData;
    }

    /**
     * 管理员修改用户信息
     * @param user
     * @return
     */
    @PostMapping("/update_user1")
    public RespData updateUser1(@RequestBody User user){
        userService.adminUpdateUser(user);
        return new RespData(RespCode.SUCCESS);
    }

    /**
     * 管理员删除用户信息
     * @param userId
     * @return
     */
    @GetMapping("/deleteUser")
    public RespData deleteUser(int userId){
        userService.deleteUserMessage(userId);
        return new RespData(RespCode.SUCCESS);
    }
}
