package tn.esprit.spring.womanarea51.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.womanarea51.entities.Comment;
import tn.esprit.spring.womanarea51.entities.Post;
import tn.esprit.spring.womanarea51.entities.User;





@Repository
public interface CommRepository extends CrudRepository<Comment, Long> {
	List<Comment> findByUserc(User u);
	List<Comment> findByPostc(Post p);
	List<Comment> findByRcomment(Comment com);
/////////
	@Query(value="select * from comment where postc_id=?1 AND userc_user_id=?2",nativeQuery=true)
	public List<Comment> getCommentaire(Long postId, Long userId);
	
	void deleteAllByPostc(Post post);
}
