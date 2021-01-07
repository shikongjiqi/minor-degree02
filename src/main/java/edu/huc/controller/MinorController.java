package edu.huc.controller;

import edu.huc.service.IMinorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class MinorController {
    @Resource
    private IMinorService minorService;

    /**
     * 跳转到index页面，同时查询一些相关辅修课程到页面
     * @return
     */
    @RequestMapping("queryMinor")
    public String queryMinor(@RequestParam(value = "page",defaultValue = "1") int page, Model model, HttpSession session){
//        RespData respData = minorService.queryMinor(page);//传递页码值，同时查询辅修课程
//        PageInfo<ResultMinor> data = (PageInfo<ResultMinor>) respData.getData();
//        model.addAttribute("list",(List<ResultMinor>) data.getList());
//        model.addAttribute(respData);
////        if ((Integer) session.getAttribute("role") == 2 || session.getAttribute("role") == null){
        return "success";
    }

    @RequestMapping("queryTeacherMinor")
    public String queryTeacherMinor(@RequestParam(value = "page",defaultValue = "1") int page, Model model, HttpSession session){
//        RespData respData = minorService.queryMinor(page);//传递页码值，同时查询辅修课程
//        PageInfo<ResultMinor> data = (PageInfo<ResultMinor>) respData.getData();
//        model.addAttribute("list",(List<ResultMinor>) data.getList());
//        model.addAttribute(respData);
        return "teacher_index";
    }
}
