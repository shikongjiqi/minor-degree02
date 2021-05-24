package edu.huc.service;

import edu.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface IScoreService {
    RespData queryMyStudent(int userId);

    RespData queryMyCourse(int userId);

    RespData queryScore(int id);

    RespData queryMyScore(int id);
}
