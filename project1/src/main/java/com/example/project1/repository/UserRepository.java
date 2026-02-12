package com.example.project1.repository;

import com.example.project1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {


   @Query ("SELECT u FROM User u WHERE u.mail = :mail AND u.password = :password")
   Optional<User> findByMailAndPassword(String mail,String password);


}
