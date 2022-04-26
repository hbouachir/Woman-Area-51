package tn.esprit.spring.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Interview;
import tn.esprit.spring.entities.InterviewKey;
@Repository
public interface InterviewRepository extends CrudRepository<Interview, Long> {

	@Query("Select u.userName FROM User u join u.interviews i where i.valid = 'Accepted' ")
	Set<String> ListOfAcceptedUser();
	@Query("Select u.userName FROM User u join u.interviews i where i.valid = 'Pending' ")
	Set<String> ListOfPendingUser();
	@Query("Select u.userName , u.email FROM User u join u.interviews i where i.valid = 'Rejected' ")
	Set<String> ListOfRejectedUser();
	@Query("Select i from Interview i where i.user.userId=:userId")
	List<Interview> listInterviewsParUser (@Param("userId") Long userId );


}
