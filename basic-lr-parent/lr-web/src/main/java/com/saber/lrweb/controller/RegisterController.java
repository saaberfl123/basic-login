package com.saber.lrweb.controller;

import com.saber.lrcommon.constant.RegisterCode;
import com.saber.lrcommon.module.Result;
import com.saber.lrcommon.module.User;
import com.saber.lrweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
