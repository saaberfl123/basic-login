package com.saber.lrweb.filter;
import com.saber.lrcommon.constant.FilterCode;
import com.saber.lrcommon.module.User;
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
public class LoginFilter extends HttpFilter
{
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session=request.getSession(false);//不创建session 防止恶意注入
        User user=  session==null ? null : (User)session.getAttribute("loginUser");
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
