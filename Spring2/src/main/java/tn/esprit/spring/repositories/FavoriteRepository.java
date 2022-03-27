package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Favorite;
import tn.esprit.spring.entities.Interview;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

	@Query("Select f from Favorite f where f.user.userId=:userId")
	List<Favorite> listFavoriteOfferParUser (@Param("userId") Long userId );
}
