package com.appInfoSystem.util;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class userInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*获取请求的URL，允许错误页面和登录页面*/
        String url = request.getRequestURI();
        if (url.contains("/dologin") || url.contains("/login") || url.contains("/logout") || url.contains("403.jsp")) {
            return true;
        }
        /*获取session，阻止未登录*/
        HttpSession session = request.getSession();
        Object userSession = session.getAttribute("userSession");
        Object devUserSession = session.getAttribute("devUserSession");
        if (userSession != null && url.contains("/manage"))
            return true;
        else if (devUserSession != null && url.contains("/dev"))
            return true;
        else {
            request.getRequestDispatcher("/403.jsp").forward(request, response);
            return false;
        }
    }
}
