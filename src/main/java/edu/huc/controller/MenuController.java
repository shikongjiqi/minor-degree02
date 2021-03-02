package edu.huc.controller;

import edu.huc.bean.Menu;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.service.IMenuService;
import edu.huc.util.JwtUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IMenuService menuService;
    @Resource
    private JwtUtils jwtUtils;

    /**
     * 获取菜单列表
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/getMenu")
    public RespData getMenu(){
        List<Menu> menuList = menuService.getMenuList(0);
        return new RespData(RespCode.SUCCESS,menuList);
    }
}
