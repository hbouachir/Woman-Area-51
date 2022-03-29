package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(CourseView.More.class)
    Long idAnswer;
    @JsonView(CourseView.More.class)
    String answer;

    @ManyToOne
    @JsonIgnore
    private Question question;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonView(CourseView.Extra.class)
    Boolean correct;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return idAnswer.equals(answer1.idAnswer) && answer.equals(answer1.answer) && correct.equals(answer1.correct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnswer, answer, correct);
    }
}
