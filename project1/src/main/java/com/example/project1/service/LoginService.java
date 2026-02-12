package com.example.project1.service;


import com.example.project1.entity.User;
import com.example.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService
{
    @Autowired
    private UserRepository userRepository;

  public Optional<User> login(String mail, String password){ return userRepository.findByMailAndPassword(mail,password); }


}
