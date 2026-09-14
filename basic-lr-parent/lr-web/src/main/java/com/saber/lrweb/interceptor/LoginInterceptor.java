package com.saber.lrweb.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saber.lrcommon.constant.FilterCode;
import com.saber.lrcommon.module.Result;
import com.saber.lrcommon.module.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("loginUser");
        Result result;
        boolean answer=true;
        if (user == null) {
            result=new Result(FilterCode.UN_LOGIN,"用户未登录",null);
            response.setContentType("text/json;charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();
            response.setStatus(403);
            response.getWriter().write(mapper.writeValueAsString(result));
            answer=false;
        }
        return answer;
    }
}
