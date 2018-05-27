package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    int order;

    @Getter
    @Setter
    String text;

    @Getter
    @Setter
    String className;

    @Getter
    @Setter
    String style;

    @Getter
    @Setter
    String width;

    @Getter
    @Setter
    String height;

    @ManyToOne
    @JsonIgnore
    @Getter@Setter
    private Topic topic;
}
