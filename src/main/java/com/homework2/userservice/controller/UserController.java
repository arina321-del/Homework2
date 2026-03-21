package com.homework2.userservice.controller;

import com.homework2.userservice.dto.UserDto;
import com.homework2.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id,
                          @RequestBody UserDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}