package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.entities.Answer;

public interface AnswerRepository extends CrudRepository<Answer,Long> {
}
