package com.saber.lrdao.dao;

import com.saber.lrcommon.module.User;

/**
 * 数据访问层接口
 */
public interface UserDao
{
    public int saveUser(String username, String password,String salt);
    public User getUserByUsername(String username);
}
