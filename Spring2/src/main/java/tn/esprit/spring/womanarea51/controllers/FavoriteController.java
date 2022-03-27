package tn.esprit.spring.womanarea51.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.Favorite;
import tn.esprit.spring.womanarea51.services.FavoriteService;

@RestController
public class FavoriteController  {
@Autowired
FavoriteService fr;

@PutMapping("/FavOffer/{idOffer}/{userId}")
public void addOfferFavorite(@PathVariable ("idOffer")Long idOffer,@PathVariable("userId")Long userId ) {
	fr.AddOfferFavorite(idOffer, userId);
	
}
@GetMapping("/ListFavoriteOfferByUser/{userId}")
public List<Favorite> listFavoriteOfferParUser(@PathVariable ("userId") Long userId){
	return fr.listFavoriteOfferParUser(userId);
}
@DeleteMapping("/DelteteFavOffer/{idOffer}/{userId}")
public void DelteteFavOffer(@PathVariable ("idOffer")Long idOffer,@PathVariable("userId")Long userId ) {
	fr.DeleteFavoriteOfferFromList(idOffer, userId);
	
}

}
