package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter@Setter
    private int id;

    @Getter@Setter
    private String title;

    @ManyToOne
    @JsonIgnore
    @Getter@Setter
    private Module module;

}
