package webdev.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
