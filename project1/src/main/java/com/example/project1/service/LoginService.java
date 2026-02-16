package com.example.project1.service;

import com.example.project1.dto.LoginDTO;
import com.example.project1.entity.User;
import com.example.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public Optional<LoginDTO> login(@RequestBody LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByMailAndPassword(loginDTO.getMail(), loginDTO.getPassword());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            LoginDTO responseDTO = new LoginDTO();
            responseDTO.setMail(user.getMail());
            return Optional.of(responseDTO);
        } else {
            return Optional.empty();
        }
    }
}
