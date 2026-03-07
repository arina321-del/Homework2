package com.homework2.userservice.service;

import com.homework2.userservice.dao.UserDao;
import com.homework2.userservice.dto.UserDto;
import com.homework2.userservice.mapper.UserMapper;
import com.homework2.userservice.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(UserDto dto) {

        User user = UserMapper.toEntity(dto);

        userDao.save(user);
    }

    public List<UserDto> getAllUsers() {

        return userDao.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {

        User user = userDao.findById(id);

        if (user != null) {
            userDao.delete(user);
        }
    }
}