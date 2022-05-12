package tn.esprit.spring.womanarea51.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.Favorite;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.FavoriteService;

@RestController
@RequestMapping("/api")
public class FavoriteController  {
	@Autowired
	FavoriteService fr;
	@Autowired
	UserRepository userRepository;

	@PreAuthorize("hasRole('USER')")
	@PutMapping("/FavOffer/{idOffer}")
	public void addOfferFavorite(@PathVariable ("idOffer")Long idOffer, Authentication authentication ) {
		User U = userRepository.findByUsername(authentication.getName()).orElse(null);
		fr.AddOfferFavorite(idOffer, U.getId());
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/ListFavoriteOfferByUser")
	public List<Favorite> listFavoriteOfferParUser( Authentication authentication){
		User U = userRepository.findByUsername(authentication.getName()).orElse(null);
		return fr.listFavoriteOfferParUser(U.getId());
	}
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/DelteteFavOffer/{idOffer}")
	public void DelteteFavOffer(@PathVariable ("idOffer")Long idOffer,Authentication authentication ) {
		User U = userRepository.findByUsername(authentication.getName()).orElse(null);
		fr.DeleteFavoriteOfferFromList(idOffer, U.getId());

	}

}
