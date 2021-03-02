package edu.huc.common.response;

public class RespData {
    private int code;//对于异常信息监控处理，200为正常

    private String msg;//存储异常信息

    private Object data;//封装数据

    private String codeDesc;

    private int count;

    public RespData(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespData(String msg) {
        this.msg = msg;
    }

    public RespData(RespCode respCode, Object data) {
        this(respCode);
        this.codeDesc=respCode.getClass().toString();
        this.data = data;
    }
    public RespData(RespCode respCode, Object data,int count) {
        this(respCode);
        this.codeDesc=respCode.getClass().toString();
        this.data = data;
        this.count=count;
    }
    public RespData(RespCode respCode, Object data,Integer count) {
        this(respCode);
        this.codeDesc=respCode.getClass().toString();
        this.data = data;
        this.count=count;
    }
    public RespData(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
