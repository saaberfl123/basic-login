package com.saber.lrweb.service;

public interface UserService
{
    public int register(String username, String password);

    public int login(String username, String password);

}
