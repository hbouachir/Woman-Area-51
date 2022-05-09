package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(CourseView.More.class)
    Long quizId;
    @JsonView(CourseView.More.class)
    int requiredToSuccess;

    @OneToMany(mappedBy="quiz", cascade = CascadeType.ALL)
    @JsonView(CourseView.More.class)
    private Set<Question> questions;

    @OneToOne(mappedBy = "quiz")
    @JsonIgnore
    Course course;
}
