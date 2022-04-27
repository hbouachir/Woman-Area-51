package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.Favorite;

public interface FavoriteService {

	public void AddOfferFavorite (Long idOffer, Long userId);
	List<Favorite> listFavoriteOfferParUser (Long userId );
	void DeleteFavoriteOfferFromList(Long idOffer, Long userId);
}
