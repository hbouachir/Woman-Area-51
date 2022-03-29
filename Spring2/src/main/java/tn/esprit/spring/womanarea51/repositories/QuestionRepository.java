package tn.esprit.spring.womanarea51.repositories;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.womanarea51.entities.Question;

public interface QuestionRepository extends CrudRepository<Question,Long> {
}
