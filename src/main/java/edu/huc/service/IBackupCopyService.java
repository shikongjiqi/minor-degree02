package edu.huc.service;

import org.springframework.stereotype.Service;

@Service
public interface IBackupCopyService {
    void insertMessage(int userId);
}
