package com.saber.lrweb.controller;

import com.saber.lrcommon.constant.LoginCode;
import com.saber.lrcommon.constant.RegisterCode;
import com.saber.lrcommon.module.User;
import com.saber.lrweb.config.BeansConfig;
import com.saber.lrweb.service.UserService;
import com.saber.lrweb.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    private UserService userService;

    //只在创建servlet的时候初始化一次容器
    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
        userService = (UserService) context.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req, resp);
    }

    //在doPost里调用doGet 修改数据都是这样必须在请求体里面给参数
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json;charset=UTF-8");
            String username=req.getParameter("username");
            String password=req.getParameter("password");
            int result=userService.register(username, password);
            if(result== RegisterCode.SUCCESS_REGISTER)resp.getWriter().write("{\"code\":"+ RegisterCode.SUCCESS_REGISTER+",\"msg\":\"注册成功\"}");
            else if(result==RegisterCode.USER_EXIST)resp.getWriter().write("{\"code\":"+ RegisterCode.USER_EXIST+",\"msg\":\"用户名已存在\"}");


        }
        catch (Exception e) {
            System.err.println("系统异常:" + e.getMessage());
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write("{\"code\":500,\"msg\":\"服务器繁忙\"}");
            e.printStackTrace();
        }

    }
}
