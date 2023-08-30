package com.example.demo.controller;

import com.example.demo.data.model.User;
import com.example.demo.data.jpa.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryMapping
    public User userById(@Argument UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @QueryMapping
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @MutationMapping
    public User createUser(@Argument String name) {
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        return user;
    }
}
