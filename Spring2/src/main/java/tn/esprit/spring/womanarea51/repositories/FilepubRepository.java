package tn.esprit.spring.womanarea51.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.Filepub;
@Repository
public interface FilepubRepository extends CrudRepository<Filepub, Long> {

}
