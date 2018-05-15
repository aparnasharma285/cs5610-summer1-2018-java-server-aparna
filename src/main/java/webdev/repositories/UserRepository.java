package webdev.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import webdev.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username=:username")
    Optional<User> findUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.email=:emailId")
    Optional<User> findUserByEmail(@PathVariable("emailId") String emailId);

    @Query("SELECT u FROM User u WHERE u.firstName=:firstName")
    List<User> findUserByFirstName(@Param("firstName") String firstName);

    @Query("SELECT u FROM User u WHERE u.lastName=:lastName")
    List<User> findUserByLastName(@Param("lastName") String lastName);

    @Query("SELECT u FROM User u WHERE u.role=:role")
    List<User> findUserByRole(@Param("role") String role);

    @Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
    Optional<User> findUserByCredentials(@Param("username") String username, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password AND u.firstName=:firstName AND u.lastName=:lastName")
    List<User> findUserByPrimaryDetails(@Param("username") String username, @Param("password") String password, @Param("firstName") String firstName, @Param("lastName") String lastName);


}
