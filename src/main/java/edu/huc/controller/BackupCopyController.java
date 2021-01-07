package edu.huc.controller;

import edu.huc.service.IBackupCopyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class BackupCopyController {
    @Resource
    private IBackupCopyService backupCopyService;

    /**
     * 管理员删除用户信息之前先将用户信息存在backup_copy表中以作备用
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("admin/insertMessage")
    public String insertNeedDeleteMessage(int userId, Model model){
        backupCopyService.insertMessage(userId);
        model.addAttribute("userId",userId);
        return "admin/delete_user";
    }
}
