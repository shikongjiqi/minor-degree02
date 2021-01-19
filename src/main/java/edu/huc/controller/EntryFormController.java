package edu.huc.controller;

import edu.huc.bean.EntryForm;
import edu.huc.common.response.RespData;
import edu.huc.service.IEntryFormService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/entryForm")
public class EntryFormController {
    @Resource
    private IEntryFormService entryFormService;

    /**
     * 学生报名
     *
     * @param minorId
     * @param name
     * @param major_name
     * @param card_id
     * @param username
     * @param average_score
     * @param interest_course
     * @param session
     * @return
     */
    @PostMapping("/apply")
    public RespData entryForm(Integer minorId,String name, String major_name, String card_id,String username,
                            Float average_score, String interest_course,HttpSession session) {//未测试
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
        return respData;
    }

    /**
     * 检验用户是否在登录状态下进行操作
     * @param session
     * @return
     */
    @GetMapping("/entryFrom")
    public RespData apply(HttpSession session){
        Integer id = (Integer)session.getAttribute("userId");
        RespData respData = entryFormService.check(id);
        return respData;
    }

    /**
     * 查询待审核的数据
     * @return
     */
    @GetMapping("/queryToAudit")
    public RespData queryToAudit(){
        RespData respData = entryFormService.queryToAudit();
        return respData;
    }

    //审核未审核的数据
    @GetMapping("/checkApply")
    public RespData checkApply(int entryFormId){
        RespData respData = entryFormService.checkApply(entryFormId);
        return respData;
    }

    //查询所有报名申请的学生数据
    @GetMapping("/queryApplyUser")
    public RespData queryApplyUser(@RequestParam(defaultValue = "1")int page){
        RespData respData = entryFormService.queryApplyUser(page);
        return respData;
    }

    //一键全部审核
    @GetMapping("/allApply")
    public RespData allApply(){
        RespData respData = entryFormService.allApply();
        return respData;
    }
}
