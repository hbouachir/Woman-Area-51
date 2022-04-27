package tn.esprit.spring.womanarea51.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.Complaint;
@Repository
public interface ComplaintRepository extends CrudRepository <Complaint,Long> {

}
