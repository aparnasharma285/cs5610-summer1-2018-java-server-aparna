package webdev.services;

import org.springframework.web.bind.annotation.*;
import webdev.models.User;
import webdev.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {

        User user = userRepository.findById(userId).orElse(null);
        return user;
    }

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {

        return userRepository.save(user);
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int userId) {

        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {

            userRepository.delete(user);
        }
    }

    @PutMapping("/api/user")
    public User updateUser(@RequestBody User user) {

        User existingUser = userRepository.findById(user.getId()).orElse(null);

        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setRole(user.getRole());

            return userRepository.save(existingUser);
        }

        return null;

    }


}
