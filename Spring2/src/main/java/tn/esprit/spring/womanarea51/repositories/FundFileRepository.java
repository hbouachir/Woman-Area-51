package tn.esprit.spring.womanarea51.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.FundFiles;
@Repository
public interface FundFileRepository extends JpaRepository<FundFiles, Long> {

}
