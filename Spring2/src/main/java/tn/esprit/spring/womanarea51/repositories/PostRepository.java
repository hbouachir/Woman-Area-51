package tn.esprit.spring.womanarea51.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.womanarea51.entities.Post;
import tn.esprit.spring.womanarea51.entities.User;



public interface PostRepository extends CrudRepository<Post, Long> {

	List<Post> findAllByOrderByIdDesc();

	List<Post> findAllByUserp(User user);

	List<Post> findByUserp(User u);
	 
	 @Query(value = "select p.title from Post p where p.title like :key", nativeQuery = true)
	 public  List<Post> findByTitleContaining( @Param("key")String key);
	 
	 @Query("SELECT s FROM Post s JOIN s.tags t WHERE t = LOWER(:tag)")
	 List<Post> retrieveByTag(@Param("tag") String tag);
	 @Query(value="select * from Post where id not in (select postc_id from comment  where postc_id is NOT NULL)", nativeQuery = true)
	    public List<Post> affichNotNullPublication();
	 
	 List<Post> findAllByOrderByScoreDesc();
	 
	 @Query(value = "SELECT * FROM Post p where p.score=(SELECT MAX(e2.score) FROM Post e2)", nativeQuery = true)
		public Post findByScore();
	 @Query(value = "SELECT * FROM Post p where p.count_com=(SELECT MAX(e2.count_com) FROM Post e2)", nativeQuery = true)
		public Post findByCountCom();
	  
}
