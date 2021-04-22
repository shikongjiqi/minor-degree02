package edu.huc.controller;

import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.service.IBackupCopyService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequiresAuthentication
@RequestMapping("/")
public class BackupCopyController {
    @Resource
    private IBackupCopyService backupCopyService;

    /**
     * 管理员删除用户信息之前先将用户信息存在backup_copy表中以作备用
     *
     * @param userId
     * @return
     */
    @GetMapping("admin/insertMessage")
    public RespData insertNeedDeleteMessage(@RequestParam int userId) {
        backupCopyService.insertMessage(userId);
        return new RespData(RespCode.SUCCESS, userId);

    }
}
