package tn.esprit.spring.womanarea51.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.Favorite;
import tn.esprit.spring.womanarea51.entities.FavoriteKey;
import tn.esprit.spring.womanarea51.entities.JobOffer;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.FavoriteRepository;
import tn.esprit.spring.womanarea51.repositories.JobOfferRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;

@Service
public class FavoriteServicempl implements FavoriteService {
@Autowired
FavoriteRepository fr ;
@Autowired
JobOfferRepository jr ;
@Autowired
UserRepository ur ;

	@Override
	public void AddOfferFavorite(Long idOffer, Long userId) {
		// TODO Auto-generated method stub
		FavoriteKey favoriteKey = new FavoriteKey() ;
		favoriteKey.setIdOffer(idOffer);
		favoriteKey.setUserId(userId);
		Favorite favorite = new Favorite() ;
		favorite.setFavoriteKey(favoriteKey);
		Optional<User> user =  ur.findById(favorite.getFavoriteKey().getUserId());
		Optional<JobOffer> offer =  jr.findById(favorite.getFavoriteKey().getIdOffer());
		favorite.setOfferTitle(offer.get().getOfferTitle());
		favorite.setDomaine(offer.get().getDomaine());
		favorite.setCompanyName(offer.get().getCompanyName());
		favorite.setEndDate(offer.get().getEndDate());
		favorite.setOfferDeadline(offer.get().getOfferDeadline());
		favorite.setOfferPlace(offer.get().getOfferPlace());
		favorite.setSalaire(offer.get().getSalaire());
		favorite.setStartDate(offer.get().getStartDate());
		favorite.setTypeContract(offer.get().getTypeContract());
		fr.save(favorite);
	}

	@Override
	public List<Favorite> listFavoriteOfferParUser(Long userId) {
		// TODO Auto-generated method stub
		return fr.listFavoriteOfferParUser(userId);
	}

	@Override
	public void DeleteFavoriteOfferFromList(Long idOffer, Long userId) {
		// TODO Auto-generated method stub
		FavoriteKey favoriteKey = new FavoriteKey() ;
		favoriteKey.setIdOffer(idOffer);
		favoriteKey.setUserId(userId);
		Favorite favorite = new Favorite() ;
		favorite.setFavoriteKey(favoriteKey);
		fr.delete(favorite);
		
	}

}
