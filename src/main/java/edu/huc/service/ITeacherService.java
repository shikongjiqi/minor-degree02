package edu.huc.service;

import edu.huc.bean.Teacher;
import edu.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface ITeacherService {
    RespData queryTeacher();

    RespData selectTeacherById(int teacherId);

    void adminUpdateTeacherMessage(Teacher teacher);

    void deleteTeacherMessage(int userId);
}
