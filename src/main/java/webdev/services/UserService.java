package webdev.services;

import org.springframework.web.bind.annotation.*;
import webdev.models.User;
import webdev.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        User existingUser = userRepository.findUserByUsername(user.getUsername()).orElse(null);

        if(existingUser == null){
            return userRepository.save(user);
        } else return null;

    }


    @PostMapping("/api/register")
    public User register(@RequestBody User user, HttpSession session, HttpServletResponse response) {
        User userExists = userRepository.findUserByUsername(user.getUsername()).orElse(null);
        if(userExists!=null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        } else {
            return createUser(user);
        }
        return null;
    }


    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int userId) {

        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {

            userRepository.delete(user);
        }
    }

    @PutMapping("/api/user/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable ("userId") int userId) {

        User existingUser = userRepository.findById(userId).orElse(null);

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

    @GetMapping("/api/search/user/{username}")
    public Optional<User> searchByUsername(@PathVariable("username") String usernmae){
            return userRepository.findUserByUsername(usernmae);
    }

    @PostMapping("/api/search/user")
    public List<User> searchUser(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String role = user.getRole();

        if ((username.length() > 0) && (password.length() > 0) && (firstName.length() > 0) && (lastName.length() > 0)) {
            if (userRepository.findUserByPrimaryDetails(username, password, firstName, lastName).size() > 0) {
                return userRepository.findUserByPrimaryDetails(username, password, firstName, lastName);
            }
        } else {
            if (username.length() > 0) {
                if (password.length() > 0) {
                    if (userRepository.findUserByCredentials(username, password).isPresent()) {
                            List<User> result = new ArrayList<User>();
                            result.add(userRepository.findUserByCredentials(username, password).get());
                            return result;

                    }
                } else {
                    if (userRepository.findUserByUsername(username).isPresent()) {
                        List<User> result = new ArrayList<User>();
                        result.add(userRepository.findUserByUsername(username).get());
                        return result;
                    }
                }
            } else {

                if (firstName.length() > 0) {
                    if (userRepository.findUserByFirstName(firstName).size()>0) {
                        return userRepository.findUserByFirstName(firstName);
                    }
                } else {
                    if (lastName.length() > 0) {
                        if (userRepository.findUserByLastName(lastName).size()>0) {
                            return userRepository.findUserByLastName(lastName);
                        }
                    } else if (userRepository.findUserByRole(role).size()>0) {
                        return userRepository.findUserByRole(role);
                    }
                }


            }
        }

        return null;
    }
}