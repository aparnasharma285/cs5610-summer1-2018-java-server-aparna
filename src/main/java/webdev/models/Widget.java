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

    @Column(unique = true)
    @Getter
    @Setter
    String name;

    @Column(name = "widgetOrder")
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


    @Getter
    @Setter
    String widgetType;

    @Getter
    @Setter
    int size;

    @Getter
    @Setter
    String href;

    @Getter
    @Setter
    String src;

    @Getter
    @Setter
    String listItems;

    @Column(columnDefinition = "varchar(32) default 'UNORDERED'")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    ListType listType = ListType.UNORDERED;


    @ManyToOne
    @JsonIgnore
    @Getter@Setter
    private Topic topic;

}
