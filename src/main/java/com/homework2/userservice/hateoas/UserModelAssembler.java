package com.homework2.userservice.hateoas;

import com.homework2.userservice.controller.UserController;
import com.homework2.userservice.model.User;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, UserModel> {

    @Override
    public UserModel toModel(User user) {

        UserModel model = new UserModel();

        model.setId(user.getId());
        model.setName(user.getName());
        model.setEmail(user.getEmail());
        model.setAge(user.getAge());

        model.add(linkTo(methodOn(UserController.class).getAll()).withRel("all-users"));
        model.add(linkTo(methodOn(UserController.class).getById(user.getId())).withSelfRel());

        return model;
    }
}