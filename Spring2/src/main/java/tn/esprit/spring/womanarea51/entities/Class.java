package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    @EmbeddedId
    ClassKey id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @MapsId("studentId")
    @JsonView(CourseView.Less.class)
    User student;

    @ManyToOne
    @JoinColumn(name="course_id")
    @MapsId("courseId")
    @JsonView(CourseView.Less.class)
    Course course;
    @JsonView(CourseView.Less.class)
    String status;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(CourseView.Less.class)
    Date date;
    @JsonView(CourseView.Less.class)
    int rating;
    @JsonView(CourseView.Less.class)
    int score;

    @Override
    public String   toString() {
        return "Class{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", rating=" + rating +
                '}';
    }
}
