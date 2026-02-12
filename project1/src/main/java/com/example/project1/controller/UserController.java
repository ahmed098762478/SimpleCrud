package com.example.project1.controller;

import com.example.project1.entity.User;
import com.example.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(User user){
        userService.createUser(user);
    }
    @GetMapping("/{id}")
    public User getUserById(int id){ return userService.getUserById(id).orElse(null); }

    @GetMapping
    public List<User> getAllUsers(){ return userService.getAllUsers(); }
}
