package edu.huc.controller;

import edu.huc.common.response.RespData;
import edu.huc.service.IMinorService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequiresAuthentication
@RequestMapping("/minor")
public class MinorController {
    @Resource
    private IMinorService minorService;

    /**
     * 跳转到index页面，同时查询一些相关辅修课程到页面
     * @return
     */
    @GetMapping("/queryMinor")
    public RespData queryMinor(@RequestParam(value = "page",defaultValue = "1") int page){
        RespData respData = minorService.queryMinor(page);//传递页码值，同时查询辅修课程
        return respData;
    }

    @PostMapping("/queryTeacherMinor")
    public RespData queryTeacherMinor(@RequestParam(value = "page",defaultValue = "1") int page){
        RespData respData = minorService.queryMinor(page);//传递页码值，同时查询辅修课程
        return respData;
    }
}
