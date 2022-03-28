package tn.esprit.spring.womanarea51.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.Interview;
import tn.esprit.spring.womanarea51.entities.User;

@Repository
public interface InterviewRepository extends CrudRepository<Interview, Long> {

	@Query("Select u FROM User u join u.interviews i where i.valid = 'Accepted' ")
	List<User> ListOfAcceptedUser();
	@Query("Select u FROM User u join u.interviews i where i.valid = 'Pending' ")
	List<User> ListOfPendingUser();
	@Query("Select u FROM User u join u.interviews i where i.valid = 'Rejected' ")
	List<User> ListOfRejectedUser();
	@Query("Select i from Interview i where i.user.id=:userId")
	List<Interview> listInterviewsParUser (@Param("userId") Long userId );
	
	
}
