package tn.esprit.spring.womanarea51.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.ExpertInterview;
@Repository
public interface ExpertInterviewRepository extends CrudRepository<ExpertInterview,Long> {
	@Query(value="SELECT user_id FROM expert_interview e INNER JOIN user u  ON u.user_id = e.expert_user_id GROUP BY user_id ORDER BY AVG(rating) DESC",nativeQuery = true)
	public List<Integer> retrieveRatings() ;
	
	@Query(value="SELECT id_expert_interview FROM expert_interview WHERE rating=?1",nativeQuery = true)
	public List<Long> retrieveBotInterview(int rating) ;
	
	@Query(value="SELECT COUNT(*) FROM expert_interview WHERE expert_user_id=?1",nativeQuery = true)
	public int countInterviews(long l) ;

}
