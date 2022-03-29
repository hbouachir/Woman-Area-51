package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(CourseView.Less.class)
    Long courseId;
    @JsonView(CourseView.Less.class)
    String nom;
    @Enumerated(EnumType.STRING)
    @JsonView(CourseView.Less.class)
    CoursePlace place;
    @Enumerated(EnumType.STRING)
    @JsonView(CourseView.Less.class)
    CourseCategory category;
    @Temporal(TemporalType.DATE)
    @JsonView(CourseView.Less.class)
    Date start_date;
    @Temporal(TemporalType.DATE)
    @JsonView(CourseView.Less.class)
    Date end_date;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    @JsonIgnore
    Set<Class> classes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    @JsonView(CourseView.More.class)
    Set<File> files;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonView(CourseView.More.class)
    Quiz quiz;

    @ManyToOne
    @JsonView(CourseView.Less.class)
   // @JsonIncludeProperties({"id","firstName","lastName","username"})
    User instructor;

}
