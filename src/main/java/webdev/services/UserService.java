package webdev.services;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import webdev.models.User;
import webdev.repositories.UserRepository;
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

    @PostMapping("/api/users")
    public User createUser(@RequestBody User user){

        return userRepository.save(user);
    }



}
