package tn.esprit.spring.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Favorite;

public interface FavoriteService {

	public void AddOfferFavorite (Long idOffer, Long userId);
	List<Favorite> listFavoriteOfferParUser (Long userId );
	void DeleteFavoriteOfferFromList(Long idOffer, Long userId);
}
