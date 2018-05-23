package webdev.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Getter@Setter
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Getter@Setter
    @JsonFormat(pattern="MMMM"+" "+"dd"+", "+"yyyy")
    private Date modified;

    @OneToMany(mappedBy="course")
    @Getter@Setter
    private List<Module> modules;


}
