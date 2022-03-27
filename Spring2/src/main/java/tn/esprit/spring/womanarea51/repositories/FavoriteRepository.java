package tn.esprit.spring.womanarea51.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.Favorite;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

	@Query("Select f from Favorite f where f.user.id=:userId")
	List<Favorite> listFavoriteOfferParUser (@Param("userId") Long userId );
}
