package edu.huc.controller;

import edu.huc.bean.EntryForm;
import edu.huc.common.response.RespData;
import edu.huc.service.IEntryFormService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class EntryFormController {
    @Resource
    private IEntryFormService entryFormService;

    /**
     * 学生报名
     * @param minorId
     * @param name
     * @param major_name
     * @param card_id
     * @param username
     * @param average_score
     * @param interest_course
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("apply")
    public String entryForm(Integer minorId,String name, String major_name, String card_id,String username,
                            Float average_score, String interest_course,HttpSession session,Model model) {
        EntryForm entryForm = new EntryForm();
        entryForm.setUserName(username);
        //将数据封装为对象，利用对象进行操作
        entryForm.setMajorName(major_name);
        entryForm.setCardId(card_id);
        entryForm.setAverageScore(average_score);
        entryForm.setInterestCourse(interest_course);
        entryForm.setMinorId(minorId);
        entryForm.setChecked(1);
        RespData respData = entryFormService.apply(entryForm, (String) session.getAttribute("username"), name,username);
        if (respData.getCode() == 0) {
            return "wait_check";
        } else {
            model.addAttribute("msg", respData.getMsg());
            return "entry_form";
        }
    }

    /**
     * 检验用户是否在登录状态下进行操作
     * @param minorId
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("entryFrom")
    public String apply(int minorId,Model model, HttpSession session){
        Integer id = (Integer)session.getAttribute("userId");
        RespData respData = entryFormService.check(id);
        if (respData.getCode() == 0){
            model.addAttribute("minorId",minorId);
            return "entry_form";
        }else{
            model.addAttribute("respData", respData);
            return "success";
        }
    }

    /**
     * 查询待审核的数据
     * @return
     */
    @RequestMapping("admin/queryToAudit")
    @ResponseBody
    public RespData queryToAudit(){
        RespData respData = entryFormService.queryToAudit();
        return respData;
    }

    //审核未审核的数据
    @RequestMapping("admin/checkApply")
    public String checkApply(int entryFormId,Model model){
        RespData respData = entryFormService.checkApply(entryFormId);
        model.addAttribute("msg", respData.getMsg());
        return "admin/success";
    }

    //查询所有报名申请的学生数据
    @RequestMapping("admin/queryApplyUser")
    @ResponseBody
    public RespData queryApplyUser(){
        RespData respData = entryFormService.queryApplyUser();
        return respData;
    }

    //一键全部审核
    @RequestMapping("admin/allApply")
    public String allApply(Model model){
        RespData respData = entryFormService.allApply();
        if (respData.getCode() == 1){
            model.addAttribute("msg", respData.getMsg());
        }
        return "admin/success" ;
    }
}
