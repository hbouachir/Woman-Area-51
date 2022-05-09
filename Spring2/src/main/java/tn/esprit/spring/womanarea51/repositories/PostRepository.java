package tn.esprit.spring.womanarea51.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.Post;
import tn.esprit.spring.womanarea51.entities.User;

@Repository

public interface PostRepository extends CrudRepository<Post, Long> {

	List<Post> findAllByOrderByIdDesc();

	List<Post> findAllByUserp(User user);

	List<Post> findByUserp(User u);
	//List<Post> findAllByOrderByUserpDesc(User u);
	/* @Query(value = "select * from Post p where p.userp=user  ORDER BY id DESC", nativeQuery = true)
	 List<Post> findAllByOrderByUserpDesc(@Param("user")Long u);*/
	 @Query(value = "select p.title from Post p where p.title like :key", nativeQuery = true)
	 public  List<Post> findByTitleContaining( @Param("key")String key);
	 
	 @Query("SELECT s FROM Post s JOIN s.tags t WHERE t = LOWER(:tag)")
	 List<Post> retrieveByTag(@Param("tag") String tag);
	 @Query(value="select * from Post where id not in (select postc_id from comment  where postc_id is NOT NULL)", nativeQuery = true)
	    public List<Post> affichNotNullPublication();
	 @Query(value="select * from Post where id  in (select postc_id from comment  where postc_id is NOT NULL)", nativeQuery = true)
	    public List<Post> affichPublication();
	 List<Post> findAllByOrderByScoreDesc();
	 
	 @Query(value = "SELECT * FROM Post p where p.score=(SELECT MAX(e2.score) FROM Post e2)", nativeQuery = true)
		public Post findByScore();
	 @Query(value = "SELECT * FROM Post p where p.count_com=(SELECT MAX(e2.count_com) FROM Post e2)", nativeQuery = true)
		public Post findByCountCom();
	 @Query(value = "select p from Post p where CONCAT(p.body,b.title,t.tags) like :keyword", nativeQuery = true)
	 public  List<Post> findAllKey( @Param("keyword")String keyword);
	  
	 
	  
}
