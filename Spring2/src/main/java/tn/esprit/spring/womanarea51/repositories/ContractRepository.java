package tn.esprit.spring.womanarea51.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract,Long> {

	
	@Query("Select c from Contract c where c.user.userId=:userId")
	List<Contract> ContractParUser (@Param("userId") Long userId );
	
}
