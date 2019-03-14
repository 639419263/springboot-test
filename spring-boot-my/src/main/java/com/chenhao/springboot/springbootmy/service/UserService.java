package com.chenhao.springboot.springbootmy.service;

import com.chenhao.springboot.springbootmy.entity.User;

import java.util.Map;

public interface UserService {
    public Map<String,Object> save(User user);

    public Map<String,Object> update(Map<String,Object> param,Object key);

    public Map<String,Object> findAll();

    public Map<String,Object> findByName(String name);

    public Map<String,Object> login(String name,String pwd);
}
