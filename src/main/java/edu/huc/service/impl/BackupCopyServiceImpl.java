package edu.huc.service.impl;

import edu.huc.bean.BackupCopy;
import edu.huc.bean.User;
import edu.huc.dao.BackupCopyMapper;
import edu.huc.dao.UserMapper;
import edu.huc.service.IBackupCopyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BackupCopyServiceImpl implements IBackupCopyService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private BackupCopyMapper backupCopyMapper;

    @Override
    public void insertMessage(int userId) {
        User deleteUser = userMapper.selectById(userId);
        BackupCopy backupCopy = new BackupCopy();
        backupCopy.setUserId(deleteUser.getUserId());
        backupCopy.setUsername(deleteUser.getUsername());
        backupCopy.setPassword(deleteUser.getPassword());
        backupCopy.setName(deleteUser.getName());
        backupCopy.setAge(deleteUser.getAge());
        backupCopy.setSex(deleteUser.getSex());
        backupCopy.setPhones(deleteUser.getPhones());
        backupCopy.setEmail(deleteUser.getEmail());
        backupCopy.setIdentity(deleteUser.getIdentity());
        backupCopyMapper.insert(backupCopy);
    }
}
