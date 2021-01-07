package edu.huc.service;

import edu.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface ITeacherService {
    RespData queryTeacher();

    RespData selectTeacherById(int teacherId);

    void adminUpdateTeacherMessage(int teacherId,String userName,String academyName,String professional);

    void deleteTeacherMessage(int userId);
}
