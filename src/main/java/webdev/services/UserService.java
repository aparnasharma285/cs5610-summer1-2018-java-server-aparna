package webdev.services;

import org.springframework.web.bind.annotation.*;
import webdev.models.User;
import webdev.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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

        if (existingUser == null) {
            return userRepository.save(user);
        } else return null;

    }

    @PostMapping("/api/register")
    public User register(@RequestBody User user, HttpSession session, HttpServletResponse response) {
        User userExists = userRepository.findUserByUsername(user.getUsername()).orElse(null);
        if (userExists != null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        } else {
            session.setAttribute("currentUser", user);
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
    public User updateUser(@RequestBody User user, @PathVariable("userId") int userId) {

        User existingUser = userRepository.findById(userId).orElse(null);

        String newUsername = user.getUsername();
        String newPassword = user.getPassword();
        String newFirstName = user.getFirstName();
        String newLastName = user.getLastName();
        String newRole = user.getRole();
        String newPhone = user.getPhone();
        String newEmail = user.getEmail();
        Date newDob = user.getDateOfBirth();

        if (existingUser != null) {
            if (newUsername != null) {
                existingUser.setUsername(newUsername);
            }
            if (newPassword != null) {
                existingUser.setPassword(newPassword);
            }
            if (newFirstName != null) {
                existingUser.setFirstName(newFirstName);
            }
            if (newRole != null) {
                existingUser.setRole(newRole);
            }
            existingUser.setLastName(newLastName);
            existingUser.setEmail(newEmail);
            existingUser.setDateOfBirth(newDob);
            existingUser.setPhone(newPhone);

            return userRepository.save(existingUser);
        }

        return null;

    }

    @GetMapping("/api/search/user/{username}")
    public Optional<User> searchByUsername(@PathVariable("username") String usernmae) {
        return userRepository.findUserByUsername(usernmae);
    }

    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

        User existingUser = userRepository.findUserByCredentials(user.getUsername(), user.getPassword()).orElse(null);

        if (existingUser != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentUser", existingUser);
            return existingUser;
        }
        response.setStatus(404);
        return null;

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
                    if (userRepository.findUserByFirstName(firstName).size() > 0) {
                        return userRepository.findUserByFirstName(firstName);
                    }
                } else {
                    if (lastName.length() > 0) {
                        if (userRepository.findUserByLastName(lastName).size() > 0) {
                            return userRepository.findUserByLastName(lastName);
                        }
                    } else if (userRepository.findUserByRole(role).size() > 0) {
                        return userRepository.findUserByRole(role);
                    }
                }


            }
        }

        return null;
    }

    @GetMapping("/api/reset/{emailId}")
    public User resetEmail(@PathVariable("emailId") String emailId) {
        return userRepository.findUserByEmail(emailId).orElse(null);

    }


    @GetMapping("/api/profile")
    public User profile(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        return currentUser;
    }

    @GetMapping("/api/session/invalidate")
    public void logoutUser(HttpSession session){

        session.invalidate();
    }


}