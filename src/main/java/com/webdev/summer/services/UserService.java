package com.webdev.summer.services;

import com.webdev.summer.models.User;
import com.webdev.summer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    @GetMapping("/api/users")
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

}
