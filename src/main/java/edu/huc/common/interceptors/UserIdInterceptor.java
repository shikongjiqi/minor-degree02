package edu.huc.common.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserIdInterceptor extends HandlerInterceptorAdapter {
    /**
     * 在请求处理之前进行调用（Controller方法调用之前） 基于URL实现的拦截器
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        Object user = request.getSession().getAttribute("account");
//        if(user==null){
//            return false;
//        }
//        return true;

        HttpSession session = request.getSession();
        if(request.getSession().getAttribute("role")==null){
            session.setAttribute("userId", 0);
            session.setAttribute("role", 3);
        }
        return true;
    }
}
