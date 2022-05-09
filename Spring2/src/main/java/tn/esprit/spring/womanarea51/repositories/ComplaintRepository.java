package tn.esprit.spring.womanarea51.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.Complaint;
@Repository
public interface ComplaintRepository extends CrudRepository <Complaint,Long> {
	@Query(value="SELECT user_user_id FROM complaint WHERE complaint_type=?1",nativeQuery = true)
	public List<Integer> retComplaintsByType(String type);

}
