package com.homework2.userservice.controller;

import com.homework2.userservice.dto.UserDto;
import com.homework2.userservice.hateoas.UserModel;
import com.homework2.userservice.hateoas.UserModelAssembler;
import com.homework2.userservice.model.User;
import com.homework2.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserModelAssembler assembler;

    public UserController(UserService service, UserModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public CollectionModel<UserModel> getAll() {

        List<UserModel> users = service.getAll()
                .stream()
                .map(dto -> {
                    User user = new User();
                    user.setId(dto.getId());
                    user.setName(dto.getName());
                    user.setEmail(dto.getEmail());
                    user.setAge(dto.getAge());
                    return assembler.toModel(user);
                })
                .toList();

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAll()).withSelfRel());
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

    @GetMapping("/{id}")
    public UserModel getById(@PathVariable Long id) {

        User user = new User();
        user.setId(id);

        return assembler.toModel(user);
    }
}