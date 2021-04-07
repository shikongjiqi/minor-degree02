package edu.huc.common.response;

public enum RespCode {
    SUCCESS(200, "请求成功"),
    WRONG(400, "服务器内部错误"),
    ENPTY(400,"未报名"),
    ERROR_INPUT(400,"输入内容有误"),
    ERROR_SESSION(401,"用户未登录或账号密码已过期"),
    ERROR_USER(400,"账户或密码错误"),
    ERROR_NETWORK(401,"网络错误"),
    WARN_INPUT(400,"输入含有违规内容"),
    ERROR_FILE(400,"上传文件错误"),
    NO_ECCESSRIGHT(400,"无权访问"),
    EXTEND_MAX_FILESIZE(400,"文件过大"),
    DUPLICATE_KEY(400,"非本人操作"),
    USER_LOCKED(400,"操作失败"),
    REPETITION(200,"已经报名"),
    WAIT_CHECK(200,"等待老师审核"),
    DOWN_ERROR(400,"下载失败"),
    DONT_FOUND(404,"未找到");

    private Integer code;
    private String msg;

    RespCode(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
