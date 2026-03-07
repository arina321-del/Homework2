package com.homework2.userservice.mapper;

import com.homework2.userservice.dto.UserDto;
import com.homework2.userservice.model.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static User toEntity(UserDto dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        user.setCreatedAt(LocalDateTime.now());

        return user;
    }

    public static UserDto toDto(User user) {

        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());

        return dto;
    }
}