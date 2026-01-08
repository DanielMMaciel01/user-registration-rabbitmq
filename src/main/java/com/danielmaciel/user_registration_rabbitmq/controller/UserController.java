package com.danielmaciel.user_registration_rabbitmq.controller;

import com.danielmaciel.user_registration_rabbitmq.model.User;
import com.danielmaciel.user_registration_rabbitmq.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    public User create(@RequestBody User user){
        return service.create(user);
    }

    @GetMapping
    public List<User> list(){
        return service.findAll();
    }
}
