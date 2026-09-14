package com.saber.lrweb.controller;

import com.saber.lrcommon.constant.LoginCode;
import com.saber.lrcommon.module.Result;
import com.saber.lrcommon.module.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
@RestController()
@RequestMapping("/user")
public class HomeController
{


    @RequestMapping("/home")
    protected ResponseEntity<Result> home(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("loginUser");
        return ResponseEntity.ok(new Result(LoginCode.SUCCESS_LOGIN, "您好:" + user.getUsername(), user));
    }
}
