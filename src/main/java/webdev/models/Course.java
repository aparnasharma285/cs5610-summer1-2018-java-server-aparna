package webdev.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter@Setter
    private int id;

    @Getter@Setter
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter@Setter
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter@Setter
    private Date modified;

    @OneToMany(mappedBy="course")
    @Getter@Setter
    private List<Module> modules;


}
