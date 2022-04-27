package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @MapsId("studentId")
    User student;

    @ManyToOne
    @JoinColumn(name="course_id")
    @MapsId("courseId")
    @JsonIgnore
    Course course;

    String status;
    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    int rating;

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
