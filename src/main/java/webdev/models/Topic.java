package webdev.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter@Setter
    private int id;

    @Getter@Setter
    private String title;

    @ManyToOne
    @JsonIgnore
    @Getter@Setter
    private Lesson lesson;
}
