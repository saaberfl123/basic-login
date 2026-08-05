package com.saber.layerd.controller;

import com.saber.layerd.constant.LoginCode;
import com.saber.layerd.constant.RegisterCode;
import com.saber.layerd.module.User;
import com.saber.layerd.service.UserService;
import com.saber.layerd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册Servlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try{
           req.setCharacterEncoding("UTF-8");
           resp.setContentType("application/json;charset=UTF-8");
           UserService service=new UserServiceImpl();
           String username=req.getParameter("username");
           String password=req.getParameter("password");
           int result=service.register(username, password);
           if(result== RegisterCode.SUCCESS_REGISTER)resp.getWriter().write("{\"code\":"+ RegisterCode.SUCCESS_REGISTER+",\"msg\":\"注册成功\"}");
           else if(result==RegisterCode.USER_EXIST)resp.getWriter().write("{\"code\":"+ RegisterCode.USER_EXIST+",\"msg\":\"用户名已存在\"}");


       }
       catch (Exception e) {
           System.err.println("系统异常:" + e.getMessage());
           resp.getWriter().write("{\"code\":500,\"msg\":\"服务器繁忙\"}");
           e.printStackTrace();
       }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
