package tn.sirine.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.sirine.spring.entities.Pub;

@Repository
public interface PubRepository extends CrudRepository<Pub, Long> {

}
