package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.event;

public interface EventRepository extends JpaRepository<event,Long>{

}
