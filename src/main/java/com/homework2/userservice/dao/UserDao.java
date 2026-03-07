package com.homework2.userservice.dao;

import com.homework2.userservice.model.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    List<User> findAll();

    User findById(Long id);

    void update(User user);

    void delete(User user);
}