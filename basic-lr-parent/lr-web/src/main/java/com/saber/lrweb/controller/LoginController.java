package com.saber.lrweb.controller;

import com.saber.lrcommon.constant.LoginCode;
import com.saber.lrcommon.module.Result;
import com.saber.lrcommon.module.User;
import com.saber.lrweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
        HttpStatus httpStatus= HttpStatus.OK;
        user=new User(user.getUsername());
        if (status == LoginCode.SUCCESS_LOGIN)
        {
            session.setAttribute("loginUser", user);//不能存明文密码
            result = new Result(LoginCode.SUCCESS_LOGIN, "登录成功", user);
        }
        else if (status == LoginCode.WRONG_PASSWORD)
        {
            httpStatus = HttpStatus.UNAUTHORIZED;
            result = new Result(LoginCode.WRONG_PASSWORD, "密码错误", user);
        }
        else
        {
            httpStatus = HttpStatus.UNAUTHORIZED;
            result = new Result(LoginCode.WRONG_USER, "用户不存在", user);
        }
        return ResponseEntity.status(httpStatus).body(result);
    }
}
