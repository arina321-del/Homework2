package com.homework2.userservice.service;

import com.homework2.userservice.dto.UserEvent;
import com.homework2.userservice.kafka.UserEventProducer;
import com.homework2.userservice.repository.UserRepository;
import com.homework2.userservice.dto.UserDto;
import com.homework2.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserEventProducer producer;

    public UserServiceImpl(UserRepository repository,
                           UserEventProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @Override
    public UserDto create(UserDto dto) {
        User user = toEntity(dto);
        User saved = repository.save(user);

        producer.sendEvent(new UserEvent("CREATE", saved.getEmail()));

        return toDto(saved);
    }

    @Override
    public List<UserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        User user = repository.findById(id).orElseThrow();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        return toDto(repository.save(user));
    }

    @Override
    public void delete(Long id) {

        User user = repository.findById(id).orElseThrow();

        repository.deleteById(id);

        producer.sendEvent(new UserEvent("DELETE", user.getEmail()));
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        return dto;
    }

    private User toEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        return user;
    }
}