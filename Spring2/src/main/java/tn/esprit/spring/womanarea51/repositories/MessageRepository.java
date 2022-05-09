package tn.esprit.spring.womanarea51.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.Mensaje;
import tn.esprit.spring.womanarea51.entities.User;





@Repository

public interface MessageRepository extends CrudRepository<Mensaje, Long>{

	@Query(value = "" +
            "SELECT m FROM Mensaje AS m " +
            "WHERE ((m.username = :firstUserId AND m.user = :secondUserId) " +
            "OR  (m.username = :secondUserId AND m.user = :firstUserId)) " )
    List<Mensaje> findAllMensajesBetweenTwoUsers(@Param("firstUserId") String firstUserId, @Param("secondUserId")String secondUserId);

	User findByUsername(String username);
//	List<Mensaje> findByToUserAndFromUser(User t,User f );
	//User findByName(String username);
	@Query(value = "" +
            "SELECT m FROM Mensaje AS m " +
            "WHERE  m.user = 'userchatt' " )
    List<Mensaje> findAllMensajes();

}
