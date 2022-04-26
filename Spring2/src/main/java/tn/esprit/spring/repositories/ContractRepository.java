package tn.esprit.spring.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Contract;
import tn.esprit.spring.entities.Interview;

@Repository
public interface ContractRepository extends CrudRepository<Contract,Long> {

	
	@Query("Select c from Contract c where c.user.userId=:userId")
	List<Contract> ContractParUser (@Param("userId") Long userId );
	
}
