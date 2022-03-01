package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.ExpertInterview;
@Repository
public interface ExpertInterviewRepository extends CrudRepository<ExpertInterview,Long> {

}
