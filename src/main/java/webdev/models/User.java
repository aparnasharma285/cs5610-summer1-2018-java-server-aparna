package webdev.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column(unique = true)
    @Getter
    @Setter
    @NotEmpty(message = "Please provide your username")
    private String username;

    @Getter
    @Setter
    @NotEmpty(message = "Please provide your password")
    private String password;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date dateOfBirth;

    @Getter
    @Setter
    private String role;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phone;


}

