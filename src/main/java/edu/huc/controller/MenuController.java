package edu.huc.controller;

import edu.huc.bean.Menu;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.service.IMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IMenuService menuService;

    /**
     * 获取菜单列表
     * @param role
     * @return
     */
    @GetMapping("/getMenu")
    public RespData getMenu(@RequestParam int role){
        List<Menu> menuList = menuService.getMenuList(role);
        return new RespData(RespCode.SUCCESS,menuList);
    }
}
