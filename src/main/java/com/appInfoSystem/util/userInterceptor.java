package com.appInfoSystem.util;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class userInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*String url = request.getRequestURI();// 获取请求的URL
        System.out.println(url);
        if (url.contains("/dologin") || url.contains("/login") || url.contains("/logout") || url.contains("403.jsp")) {
            return true;
        }
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("userSession");
        if (obj != null)
            return true;
        request.getRequestDispatcher("/403.jsp").forward(request, response);
        return false;*/return true;
    }
}
