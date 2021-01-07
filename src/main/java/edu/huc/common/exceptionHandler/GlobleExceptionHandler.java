package edu.huc.common.exceptionHandler;

import edu.huc.common.response.RespCode;
import edu.huc.common.response.RespData;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {
    //异常处理(@ControllerAdvice注解注释的controller层和此注解注释的方法,会对所有controller层抛出的异常进行统一处理)
    @ExceptionHandler(value = Exception.class)
    public RespData ExceptionHandler(HttpServletRequest request, Exception e) {
        System.out.println("异常处理");
        if (e instanceof ConstraintViolationException){
            ConstraintViolationException ex = (ConstraintViolationException)e;
            return new RespData(RespCode.ERROR_INPUT,ex.getMessage());
        }
        else if(e instanceof UnsupportedEncodingException
                ||e instanceof NoSuchAlgorithmException){
            return new RespData(RespCode.ERROR_INPUT);
        }else if (e instanceof MaxUploadSizeExceededException){
            return new RespData(RespCode.EXTEND_MAX_FILESIZE);
        }else if (e instanceof DuplicateKeyException){
            return new RespData(RespCode.DUPLICATE_KEY);
        }
        else {
            //TODO:测试环境
            e.printStackTrace();
            return new RespData(RespCode.WRONG,e.toString());
            //TODO:生产环境
            //return new ResEntity(RespCode.WRONG);
        }
    }
}