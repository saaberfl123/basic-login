package com.saber.lrdao.dao.impl;

import com.saber.lrcommon.module.User;
import com.saber.lrdao.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Primary
public class UserDaoImpl implements UserDao
{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveUser(String username, String password, String salt)  {
        String sql="INSERT INTO `user` (`username`, `password`, `salt`) VALUES (?,?,?);";
        Object[] params={username,password,salt};
        return jdbcTemplate.update(sql,params);
    }

    @Override
    public User getUserByUsername(String username) {
        String sql="SELECT * FROM `user` WHERE `username` = ?;";
        List<User> list=jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                username);
        return list.isEmpty() ? null:list.get(0);
    }

}
