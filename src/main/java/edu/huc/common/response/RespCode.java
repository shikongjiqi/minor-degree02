package edu.huc.common.response;

public enum RespCode {
    SUCCESS(0, "请求成功"),
    WRONG(1, "服务器内部错误"),
    ENPTY(3,"未报名"),
    ERROR_INPUT(4,"输入内容有误"),
    ERROR_SESSION(5,"用户未登录或账号密码已过期"),
    ERROR_USER(6,"账户或密码错误"),
    ERROR_NETWORK(7,"网络错误"),
    WARN_INPUT(10,"输入含有违规内容"),
    ERROR_FILE(11,"上传文件错误"),
    NO_ECCESSRIGHT(12,"无权访问"),
    EXTEND_MAX_FILESIZE(13,"文件过大"),
    DUPLICATE_KEY(14,"非本人操作"),
    USER_LOCKED(15,"操作失败"),
    REPETITION(16,"已经报名"),
    WAIT_CHECK(17,"等待老师审核"),
    DOWN_ERROR(18,"下载失败");

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
