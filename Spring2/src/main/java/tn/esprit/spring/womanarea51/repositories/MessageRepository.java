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
            "WHERE ((m.username = :firstUserId AND m.username = :secondUserId) " +
            "OR  (m.username = :secondUserId AND m.username = :firstUserId)) " )
    List<Mensaje> findAllMensajesBetweenTwoUsers(@Param("firstUserId") String firstUserId, @Param("secondUserId")String secondUserId);

	User findByUsername(String username);

	//User findByName(String username);
}
