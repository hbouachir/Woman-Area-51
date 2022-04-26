package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.feedback;

public interface feedbackRepository extends JpaRepository<feedback,Long> {

}
