package edu.huc.service;

import edu.huc.bean.Student;
import edu.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface IStudentService {
    RespData queryStudent(int page);

    RespData selectMessage(int studentId);

    void adminUpdateMessage(Student student);

    void deleteStudentMessage(int studentId);
}
