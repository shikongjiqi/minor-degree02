package edu.huc.controller;

import edu.huc.bean.User;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
//该注解用来校验用户是否登录
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    //退出账号
    @GetMapping("/logout")
    @RequiresAuthentication
    public RespData logout(){
        SecurityUtils.getSubject().logout();
        //返回登录页
        return new RespData(RespCode.SUCCESS);
    }

    /**
     * 注销账号删除账号信息
     * @return
     */
    @GetMapping("/delUser")
    @RequiresAuthentication
    public RespData delUser(HttpSession session){
        int id = (Integer)session.getAttribute("userId");
        RespData respData = userService.delUser(id);
        return respData;
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
