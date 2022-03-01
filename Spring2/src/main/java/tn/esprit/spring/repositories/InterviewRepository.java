package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Interview;

@Repository
public interface InterviewRepository extends CrudRepository<Interview,Long> {
	
	@Query("Select i from Interview i where i.user.userId=:userId")
	List<Interview> listInterviewsParUser (@Param("userId") Long userId );
	@Query("Select i from Interview i where i.jobOffer.idOffer=:idOffer and i.user.userId=:userId ")
	List<Interview> listInterviewsParOfferUser (@Param("idOffer") Long idOffer,@Param("userId") Long userId );

}