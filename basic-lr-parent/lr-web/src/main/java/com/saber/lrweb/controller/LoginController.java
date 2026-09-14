package com.saber.lrweb.controller;

import com.mysql.cj.xdevapi.Session;
import com.saber.lrcommon.constant.LoginCode;
import com.saber.lrcommon.module.Result;
import com.saber.lrcommon.module.User;
import com.saber.lrweb.config.BeansConfig;
import com.saber.lrweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//相当于Controller层
@RestController
@RequestMapping("/api")
public class LoginController
{
    private final UserService userService;

    //构造注入
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login")
    protected ResponseEntity<Result> login
            (
                    @RequestBody User user,
                    HttpSession session
            )
    {
        Result result;
        int status = userService.login(user.getUsername(), user.getPassword());
        user=new User(user.getUsername());
        if (status == LoginCode.SUCCESS_LOGIN) {
            session.setAttribute("loginUser", user);//不能存明文密码
            result = new Result(LoginCode.SUCCESS_LOGIN, "登录成功", user);
        } else if (status == LoginCode.WRONG_PASSWORD) result = new Result(LoginCode.WRONG_PASSWORD, "密码错误", user);
        else result = new Result(LoginCode.WRONG_USER, "用户不存在", user);
        return ResponseEntity.ok(result);
    }
}
