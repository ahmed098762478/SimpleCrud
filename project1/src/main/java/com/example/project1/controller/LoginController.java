package com.example.project1.controller;


import com.example.project1.dto.LoginDTO;
import com.example.project1.entity.User;
import com.example.project1.repository.UserRepository;
import com.example.project1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("*")
public class LoginController {

@Autowired
private LoginService loginService;

@PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
      Optional<LoginDTO> result = loginService.login(loginDTO);
      if(result.isPresent()){
          return ResponseEntity.ok(result.get());
      }
      else{
          return ResponseEntity.status(401).body("Invalid email or password");
      }
    }


}

