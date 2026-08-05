package com.saber.layerd.filter;



import com.saber.layerd.constant.FilterCode;
import com.saber.layerd.module.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录过滤器 过滤所有/user包下的内容
 */
@WebFilter("/user/*")
public class loginFilter extends HttpFilter
{
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("loginUser");
        if(user!=null)
        {
            chain.doFilter(request, response);
        }
        else
        {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"code\":"+ FilterCode.UN_LOGIN+",\"msg\":\"未登录，请先登录\"}");

        }
    }
}
