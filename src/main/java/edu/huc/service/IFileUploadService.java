package edu.huc.service;

import edu.huc.common.response.RespData;
import edu.huc.common.vo.FileVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public interface IFileUploadService {

    FileVo uploadFile(MultipartFile file, String userName);

    boolean isImg(MultipartFile file) throws IOException;

    RespData check(int id);

    RespData downFile(String filename, HttpServletResponse res);
}
