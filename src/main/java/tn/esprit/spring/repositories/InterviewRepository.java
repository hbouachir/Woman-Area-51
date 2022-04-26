package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Interview;

@Repository
public interface InterviewRepository extends CrudRepository<Interview,Long> {

}