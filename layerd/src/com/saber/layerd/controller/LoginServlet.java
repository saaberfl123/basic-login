package com.saber.layerd.controller;

import com.mysql.cj.Session;
import com.saber.layerd.constant.LoginCode;
import com.saber.layerd.module.User;
import com.saber.layerd.service.UserService;
import com.saber.layerd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//相当于Controller层
@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //设置JSON返回格式
            resp.setContentType("application/json;charset=utf-8");
            req.setCharacterEncoding("utf-8");//防止中文乱码
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            UserService userService = new UserServiceImpl();
            int result = userService.login(username, password);
            if (result== LoginCode.SUCCESS_LOGIN) {
                resp.getWriter().write("{\"code\":"+LoginCode.SUCCESS_LOGIN+",\"msg\":\"登录成功\"}");
                //获取session并存入对象数据
                HttpSession session=req.getSession();
                session.setAttribute("loginUser",new User(username,password));
            }
            else if(result==LoginCode.WRONG_PASSWORD) resp.getWriter().write("{\"code\":"+LoginCode.WRONG_PASSWORD+",\"msg\":\"密码错误\"}");
            else resp.getWriter().write("{\"code\":"+LoginCode.WRONG_USER+",\"msg\":\"用户不存在\"}");
        }
        catch (Exception e) {
            System.err.println("系统异常:" + e.getMessage());
            resp.getWriter().write("{\"code\":500,\"msg\":\"服务器繁忙\"}");
            e.printStackTrace();
        }
    }
}
