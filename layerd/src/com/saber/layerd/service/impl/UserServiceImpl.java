package com.saber.layerd.service.impl;

import com.saber.layerd.Dao.impl.UserDaoImpl;
import com.saber.layerd.Util.SaltUtil;
import com.saber.layerd.constant.LoginCode;
import com.saber.layerd.constant.RegisterCode;
import com.saber.layerd.module.User;
import com.saber.layerd.service.UserService;

/**
 * 业务层
 */
public class UserServiceImpl implements UserService
{
    //service层调用Dao层
    UserDaoImpl userDaoImpl = new UserDaoImpl();

    @Override
    public int register(String username, String password)
    {
        String salt = SaltUtil.generateSalt(30);
        String realPassword = SaltUtil.md5WithSalt(password, salt);
        User user = userDaoImpl.getUserByUsername(username);
        int result=-1;
        if(user == null)//不存在这样的用户名
        {
            result=userDaoImpl.saveUser(username, realPassword, salt);
            if(result>0)result=RegisterCode.SUCCESS_REGISTER;//存储成功
        }
        else result= RegisterCode.USER_EXIST;//用户已经存在
       return result;

    }

    @Override
    public int login(String username, String password)
    {
        User user = userDaoImpl.getUserByUsername(username);
        if(user==null)return LoginCode.WRONG_USER;
        String realPassword = user.getPassword();
        String InPassword= SaltUtil.md5WithSalt(password, user.getSalt());
        if(realPassword.equals(InPassword))return LoginCode.SUCCESS_LOGIN;
        return LoginCode.WRONG_PASSWORD;
    }
}
