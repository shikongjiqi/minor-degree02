package edu.huc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.huc.bean.EntryForm;
import edu.huc.bean.User;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.result.FileResult;
import edu.huc.dao.EntryFormMapper;
import edu.huc.dao.UserMapper;
import edu.huc.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements IFileUploadService {
    @Resource
    private EntryFormMapper entryFormMapper;
    @Resource
    private UserMapper userMapper;

    @Value("${local-file-directory}")
    String base;

    @Value("${file-prefix}")
    String prefix;

    // 保存图片文件夹
    private static final String IMAGES = "images";

    /**
     * 上传到文件夹
     *
     * @param type 图片类型
     * @return
     */
    private String getUploadPath(String type) {
        if (base != null) {
            File path = new File(base,IMAGES);
            path.mkdirs();
            return path.getAbsolutePath();
        }
        return null;
    }

    /**
     *
     * @param file
     * @return 成功返回文件信息，失败返回-null
     */
    @Override
    public FileResult uploadFile(MultipartFile file, String userName) {
        String path = getUploadPath(IMAGES);
        FileResult result = new FileResult(false, null);
        if (path != null && file != null && !file.isEmpty()) {
            long realSize = file.getSize();
            result.setFileSize(realSize);
            String fileName = file.getOriginalFilename();
            result.setFileName(fileName);
            String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
            result.setExtName(extName);
            String uuid = UUID.randomUUID().toString();
            String newFileName = uuid + "." + extName;
            result.setServerPath(IMAGES + "/" + newFileName);
            File dest = new File(path, newFileName);
            try {
                file.transferTo(dest);
                result.setSuccess(true);
            } catch (IllegalStateException | IOException e) {
                result.setMsg("文件上传失败！" + e.getMessage());
            }
            entryFormMapper.updateEntryFormReserved1(newFileName ,userName);
        }
        result.setServerPath(prefix+result.getServerPath());
        result.setSrc(result.getServerPath());
        return result;
    }

    @Override
    public boolean isImg(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        byte[] b = new byte[4];
        is.read(b, 0, b.length);
        StringBuilder builder = new StringBuilder();
        String hv;
        for (int i = 0; i < b.length; i++) {
            hv = Integer.toHexString(b[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        String s=builder.toString();
        //照片文件头为png或jepg
        if(s.substring(0,6).equals("FFD8FF")||s.equals("89504E47")) {
            System.out.println(s.substring(0, 6));
            System.out.println(s);
            return true;
        }
        return false;
    }

    /**
     * 检验用户是否报名成功
     * @param id
     * @return
     */
    @Override
    public RespData check(int id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        User user = userMapper.selectById(id);
        queryWrapper.eq("username",user.getUsername());
        EntryForm entryForm = entryFormMapper.selectOne(queryWrapper);
        if (entryForm == null)
            return new RespData(RespCode.ENPTY);
        return new RespData(RespCode.SUCCESS,entryForm);
    }

    /**
     * 文件下载
     * @param fileName
     * @param response
     * @return
     */
    @Override
    public RespData downFile(String fileName, HttpServletResponse response) {
        String downloadFilePath = "/root/file/";//被下载的文件在服务器中的路径,
        File file = new File(downloadFilePath);
        if (file.exists()) {
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return new RespData(RespCode.SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new RespData(RespCode.DOWN_ERROR);
    }
}
