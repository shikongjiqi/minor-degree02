package edu.huc.controller;

import edu.huc.bean.EntryForm;
import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import edu.huc.common.vo.FileVo;
import edu.huc.service.IFileUploadService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiresAuthentication
public class FileController {
    @Resource
    private IFileUploadService fileUploadService;

    /**
     * 文件上传--图片
     * @param file
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @PostMapping(value="upload",consumes = "multipart/form-data")
    public RespData upload(@RequestParam("fileName") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
        Integer id = (Integer)request.getSession().getAttribute("userId");
        if (id == null || id == 0)//检验用户是否登录
            return new RespData(RespCode.ERROR_SESSION);
        boolean flag = fileUploadService.isImg(file);
        if (!flag)//判断文件上传的是否为图片
            return new RespData(RespCode.ERROR_FILE);
        RespData respData = fileUploadService.check(id);
        if (respData.getCode() != 0){//检验用户是否已经报名
            return respData;
        }
        EntryForm data = (EntryForm) respData.getData();
        FileVo fileResult = fileUploadService.uploadFile(file,data.getUserName());
        return new RespData(RespCode.SUCCESS,fileResult);
    }

    /**
     * 文件下载
     * @param filename
     * @throws IOException
     */
    @RequestMapping(value = "downLoad")
    public RespData download(@RequestParam(value = "filename",defaultValue = "张浩东.docx") String filename) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(filename);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type",type);
        // 设置编码
        String chartSet = new String(filename.getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + chartSet);
        RespData respData = fileUploadService.downFile(filename, response);
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
        return respData;
    }
}
