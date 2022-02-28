package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.fundCategory;

public interface FundCategoryRepository  extends JpaRepository<fundCategory,Long> {

}
