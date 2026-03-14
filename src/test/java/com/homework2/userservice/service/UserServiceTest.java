package com.homework2.userservice.service;

import com.homework2.userservice.dao.UserDao;
import com.homework2.userservice.dto.UserDto;
import com.homework2.userservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserDao userDao;
    private UserService userService;

    @BeforeEach
    void setUp() {

        userDao = Mockito.mock(UserDao.class);

        userService = new UserService(userDao);
    }

    @Test
    void createUser_shouldCallDaoSave() {

        UserDto dto = new UserDto();

        dto.setName("Anna");
        dto.setEmail("anna@test.com");
        dto.setAge(25);

        userService.createUser(dto);

        verify(userDao, times(1)).save(any(User.class));
    }
}