package tn.esprit.spring.womanarea51.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.Comment;
import tn.esprit.spring.womanarea51.entities.Like;
import tn.esprit.spring.womanarea51.entities.User;



@Repository
public interface LikeRepository extends CrudRepository<Like, Long>{

  
  //  List<Likee> findAllByPostr(Post post);
	Like findByUserlikeAndComment(User user, Comment com);
	List<Like> findAllByComment(Comment com);


}
