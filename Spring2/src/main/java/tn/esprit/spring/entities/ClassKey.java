package tn.esprit.spring.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ClassKey implements Serializable {
    @Column(name = "userId")
    Long userId;

    @Column(name = "course_id")
    Long courseId;
}
