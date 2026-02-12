package com.example.project1.controller;


import com.example.project1.dto.LoginDTO;
import com.example.project1.entity.User;
import com.example.project1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Optional<User>  login(@RequestBody LoginDTO loginDTO){ return loginService.login(loginDTO.getMail(), loginDTO.getPassword()); }

}

