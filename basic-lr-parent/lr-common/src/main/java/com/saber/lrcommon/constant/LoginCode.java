package com.saber.lrcommon.constant;

/**
 * 登录状态码
 * 用于区分各种状态
 */
public class LoginCode
{
    private LoginCode(){};

    //登录成功
    public static final int SUCCESS_LOGIN = 1000;

    //用户不存在
    public static final int WRONG_USER = 1001;

    //密码错误
    public static final int WRONG_PASSWORD = 1002;


}
