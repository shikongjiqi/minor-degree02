package edu.huc.common.constant;

public class Const {
    // 不验证URL anon：不验证/authc：受控制的
    public static final String NO_INTERCEPTOR_PATH = ".*/((.css)|(.js)|(images)|(error)|(jquery-easyui-1.8.8)|(login)|(public)|(logout)|(anon)|(redirect)).*";
    public static final String BACK ="/((back))/.*";
    public static final String FRONT ="/((front))/.*";
    public static final String DEFAULT_HEADPIC ="http://img.qqzhi.com/uploads/2018-12-10/132631700.jpg";
}
