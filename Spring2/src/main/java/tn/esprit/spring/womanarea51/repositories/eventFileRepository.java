package tn.esprit.spring.womanarea51.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.eventFile;
@Repository
public interface eventFileRepository extends JpaRepository<eventFile,Long>{

}
