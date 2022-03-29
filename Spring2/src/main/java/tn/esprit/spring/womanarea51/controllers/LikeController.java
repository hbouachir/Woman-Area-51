package tn.esprit.spring.womanarea51.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;
import tn.esprit.spring.womanarea51.services.ILikeService;



@RestController
public class LikeController {
	@Autowired
	ILikeService likeService;
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/like")
	public void like ( Authentication authentication, @RequestParam("idCom")  Long idCom) {
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
	    User U = userRepository.findByUsername(U1.getUsername())
	            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		
		if(likeService.addLike( idCom,U.getId())==false){
			System.out.println("deja liked");
		}else 	System.out.println("like ajouter ....");
	}
	@PostMapping("/likesouscom")
	public void likesouscom ( Authentication authentication, @RequestParam("idCom")  Long idCom) {
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
	    User U = userRepository.findByUsername(U1.getUsername())
	            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		
		if(likeService.addLikeSouCom( idCom,U.getId())==false){
			System.out.println("deja liked");
		}else 	System.out.println("like ajouter ....");
	}
	@PutMapping("/updatelikesouscom")
	public void updatelikesouscom ( Authentication authentication, @RequestParam("idCom")  Long idCom) {
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
	    User U = userRepository.findByUsername(U1.getUsername())
	            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		
		if(likeService.deleteLikeSouCom( idCom,U.getId())==false){
			System.out.println("like introuvable");
		}else 	System.out.println("like retirer ....");
	}

	@GetMapping("/count")
	@ResponseBody
	int nombre(@RequestParam("idCom") Long idCom){
		return likeService.getAllLikesForCom(idCom);
	}
	
}
