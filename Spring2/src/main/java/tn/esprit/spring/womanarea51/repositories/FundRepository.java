package tn.esprit.spring.womanarea51.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.womanarea51.entities.fund;

public interface FundRepository  extends JpaRepository<fund,Long>{
	
	@Query("SELECT f FROM fund f JOIN f.tags t WHERE t = LOWER(:tag)")
	List<fund> retrieveByTag(@Param("tag") String tag);

}
