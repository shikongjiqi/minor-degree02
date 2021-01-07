package edu.huc.controller;

import edu.huc.bean.User;
import edu.huc.common.response.RespData;
import edu.huc.common.result.ResultAdminUser;
import edu.huc.common.result.ResultUser;
import edu.huc.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Resource
    private IUserService userService;

    /**
     * 登录
     * @param username
     * @param password
     * @param model
     * @param request
     * @return
     */
    @PostMapping("login")
    public String login(String username, String password, Model model, HttpServletRequest request){
        RespData respData = userService.login(username, password);//根据账号、密码登录
        if (respData.getCode() == 0){//判断用户是否查询成功
            ResultUser user = (ResultUser) respData.getData();
            HttpSession session = request.getSession();//获取session
            //将用户id和用户名存储到session域中
            session.setAttribute("userId",user.getUserId());
            session.setAttribute("username",user.getUserName());
            if ("学生".equals(user.getIdentity())){
                session.setAttribute("role",2);
                return "index";
            }else{
                session.setAttribute("role",1);
                model.addAttribute("respData",respData);
                return "teacher_index";
            }
        }else{
            model.addAttribute("msg", respData.getMsg());
            return "login";
        }
    }

    /**
     * 注册,将数据封装为我们所需要的user对象
     * @param username
     * @param password
     * @param name
     * @param sex
     * @param age
     * @param phones
     * @param email
     * @param identity
     * @param model
     * @return
     */
    @RequestMapping("register")
    public String register1(String username, String password,String name,String sex,String card_id,int age,String phones,String email,String identity,Model model){
        RespData respData = userService.register(username,password,name,sex,card_id,age,phones,email,identity,model);//向数据库中添加用户数据
        if (respData.getCode() == 0){
            model.addAttribute("msg", respData.getMsg());
            return "login";
        }else{
            model.addAttribute("msg", respData.getMsg());
            return "redirect:register";
        }
    }

    //退出账号
    @RequestMapping("editUser")
    public String editUser(HttpSession session){
        //移除session中的数据
        session.removeAttribute("userId");
        session.removeAttribute("username");
        session.removeAttribute("role");
        //返回登录页
        return "index";
    }

    /**
     * 注销账号删除账号信息
     * @return
     */
    @RequestMapping("delUser")
    public String delUser(HttpSession session,Model model){
        int id = (Integer)session.getAttribute("userId");
        RespData respData = userService.delUser(id);
        model.addAttribute("msg", respData.getMsg());
        return "index";
    }

    /**
     * 管理员登录
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("admin/login")
    public String loginAdmin(String username,String password,Model model,HttpSession session){
        RespData respData = userService.login(username, password);
        if (respData.getCode() == 0){
            ResultUser user = (ResultUser) respData.getData();
            session.setAttribute("userId",user.getUserId());
            session.setAttribute("username",user.getUserName());
            if ("admin".equals(user.getIdentity())){
                session.setAttribute("role",0);
                return "admin/success";
            }
        }
        model.addAttribute("msg","管理员账号密码输入错误");
        return "admin/index";
    }

    /**
     * 退出管理员
     * @return
     */
    @RequestMapping("admin/editAdmin")
    public String editAdmin(HttpSession session){
        session.removeAttribute("userId");
        session.removeAttribute("username");
        session.removeAttribute("role");
        return "admin/index";
    }

    /**
     * 管理员查询用户信息
     * @param page
     * @return
     */
    @RequestMapping(value = "admin/queryUser")
    @ResponseBody
    public RespData adminQueryUser(@RequestParam(value = "page",defaultValue = "1") int page){
        RespData respData = userService.adminQueryUser(page);
        return respData;
    }

    /**
     * 管理员查询需要修改的用户的信息
     * @param
     * @return
     */
    @RequestMapping("admin/update_user")
    public String updateUser(int userId,Model model){
        RespData respData = userService.adminSelectById(userId);
        model.addAttribute("user",(ResultAdminUser) respData.getData());
        return "admin/update_user";
    }

    /**
     * 管理员修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("admin/update_user1")
    public String updateUser1(User user){
        userService.adminUpdateUser(user);
        return "admin/success";
    }

    /**
     * 管理员删除用户信息
     * @param userId
     * @return
     */
    @RequestMapping("admin/deleteUser")
    public String deleteUser(int userId){
        userService.deleteUserMessage(userId);
        return "admin/success";
    }
}
