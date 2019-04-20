package com.zft.service.impl;

import com.zft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(String name, Integer age) {
        jdbcTemplate.update("insert into user(name,age) values (?,?)", name, age);
    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public Integer getAllUsers() {
        return null;
    }

    @Override
    public void deleteAllUsers() {

    }
}
