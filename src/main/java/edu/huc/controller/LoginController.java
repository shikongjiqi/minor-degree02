package edu.huc.controller;

import edu.huc.bean.User;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.result.ResultUser;
import edu.huc.service.IUserService;
import edu.huc.util.JwtUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Resource
    private IUserService userService;
    @Resource
    private JwtUtils jwtUtils;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public RespData login(@RequestBody User user, HttpServletResponse response){
        ResultUser result = userService.login(user.getUsername(), user.getPassword());//根据账号、密码登录
        String jwt = jwtUtils.generateToken(result.getUserId());
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Header","Authorization");
        return new RespData(RespCode.SUCCESS,result);
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
}
