package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.fund;

public interface FundRepository  extends JpaRepository<fund,Long>{

}
