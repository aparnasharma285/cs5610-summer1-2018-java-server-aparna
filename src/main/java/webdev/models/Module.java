package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Module {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter@Setter
    private int id;

    @Getter@Setter
    private String title;

    @ManyToOne
    @JsonIgnore
    @Getter@Setter
    private Course course;

    @OneToMany(mappedBy="module")
    @Getter@Setter
    private List<Lesson> lessons;

}
