package tn.esprit.spring.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long courseId;

    String nom;
    @Enumerated(EnumType.STRING)
    CoursePlace place;
    @Enumerated(EnumType.STRING)
    CourseCategory category;
    @Temporal(TemporalType.DATE)
    Date start_date;
    @Temporal(TemporalType.DATE)
    Date end_date;

    @OneToMany(mappedBy = "course")
    Set<Class> classes;

}
