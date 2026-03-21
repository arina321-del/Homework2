package com.homework2.userservice.service;

import com.homework2.userservice.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto create(UserDto dto);

    List<UserDto> getAll();

    UserDto update(Long id, UserDto dto);

    void delete(Long id);
}