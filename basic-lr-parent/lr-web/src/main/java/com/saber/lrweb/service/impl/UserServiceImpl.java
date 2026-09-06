package com.saber.lrweb.service.impl;

import com.saber.lrdao.dao.UserDao;
import com.saber.lrdao.dao.impl.UserDaoImpl;
import com.saber.lrcommon.util.SaltUtil;
import com.saber.lrcommon.constant.LoginCode;
import com.saber.lrcommon.constant.RegisterCode;
import com.saber.lrcommon.module.User;
import com.saber.lrweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 业务层
 */
@Service
@Primary
public class UserServiceImpl implements UserService
{
    //service层调用Dao层 交付Spring管理 建议命名为final防止修改
    private final UserDao userDao;

    //使用方法注入而非字段注入
    @Autowired
    public UserServiceImpl(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Transactional(rollbackFor = Exception.class)//事务管理
    @Override
    public int register(String username, String password)
    {
        String salt = SaltUtil.generateSalt(30);
        String realPassword = SaltUtil.md5WithSalt(password, salt);
        User user = userDao.getUserByUsername(username);
        int result = -1;
        if (user == null)//不存在这样的用户名
        {
            result = userDao.saveUser(username, realPassword, salt);
            if (result > 0) result = RegisterCode.SUCCESS_REGISTER;//存储成功
        } else result = RegisterCode.USER_EXIST;//用户已经存在
        return result;

    }

    //登录不需要事务管理 因为不会涉及读数据
    @Override
    public int login(String username, String password)
    {
        User user = userDao.getUserByUsername(username);
        if (user == null) return LoginCode.WRONG_USER;
        String realPassword = user.getPassword();
        String InPassword = SaltUtil.md5WithSalt(password, user.getSalt());
        if (realPassword.equals(InPassword)) return LoginCode.SUCCESS_LOGIN;
        return LoginCode.WRONG_PASSWORD;
    }
}
