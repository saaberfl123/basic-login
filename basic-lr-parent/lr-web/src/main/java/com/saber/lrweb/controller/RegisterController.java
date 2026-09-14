package com.saber.lrweb.controller;

import com.saber.lrcommon.constant.RegisterCode;
import com.saber.lrcommon.module.Result;
import com.saber.lrcommon.module.User;
import com.saber.lrweb.config.BeansConfig;
import com.saber.lrweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册Servlet
 */
@RestController
@RequestMapping("/api")
public class RegisterController
{
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    protected ResponseEntity<Result> register
            (
                @RequestBody User user
            )
    {

        int status = userService.register(user.getUsername(), user.getPassword());
        user=new User(user.getUsername());
        Result result;
        if (status == RegisterCode.SUCCESS_REGISTER)
            result = new Result(RegisterCode.SUCCESS_REGISTER, "注册成功", user);
        else result = new Result(RegisterCode.USER_EXIST, "用户名已存在", user);
        return ResponseEntity.ok(result);


    }
}
