package tn.esprit.spring.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Class {

    @EmbeddedId
    ClassKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    User student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name="courseId")
    Course course;

    String status;
    @Temporal(TemporalType.DATE)
    Date date;


}
