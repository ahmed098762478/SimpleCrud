package com.example.project1.service;

import com.example.project1.entity.User;
import com.example.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

     public void createUser(User user){
         userRepository.save(user);
     }

     public Optional<User> getUserById(int id){
         return userRepository.findById(id);

     }

     public List<User> getAllUsers(){
         return userRepository.findAll();
     }


}
