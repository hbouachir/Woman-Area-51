package tn.esprit.spring.womanarea51.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.womanarea51.entities.event;

public interface EventRepository extends JpaRepository<event,Long>{
	
	@Query("SELECT e FROM event e JOIN e.tags t WHERE t =:tag")
	List<event> retrieveByTag(@Param("tag") String tag);
	
	
	@Query("SELECT e FROM event e WHERE e.eventDateEnd BETWEEN :dateinf AND :datesup")
	List<event> retrieveEventOfTheMonth(@Param("dateinf") Date dateInf, @Param("datesup") Date dateSup );

}
