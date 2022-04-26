package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.donation;


public interface DonationRepository  extends JpaRepository<donation,Long>{

}
