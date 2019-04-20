package com.zft.service;

/**
 * user service
 */
public interface UserService {

    void insert(String name, Integer age);

    void deleteByName(String name);

    Integer getAllUsers();

    void deleteAllUsers();
}
