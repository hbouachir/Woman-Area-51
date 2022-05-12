package tn.esprit.spring.womanarea51.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.Comment;
import tn.esprit.spring.womanarea51.entities.Post;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;
import tn.esprit.spring.womanarea51.services.ICommService;


@RestController
public class CommController {

	@Autowired
	ICommService ics;
	@Autowired
	UserRepository userRepository;
	
	/////ook
	@PostMapping("/ajoutercomm")
	public ResponseEntity<?> ajouterCom( @RequestBody Comment c ,Authentication authentication, @RequestParam("idPost")  Long idPost) {
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
	    User U = userRepository.findByUsername(U1.getUsername())
	            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		ics.addCommentaire( c, U.getId(),  idPost);
		return 	ResponseEntity.ok().body(c);
	}
	

	//commentaire par user  ookk
	@GetMapping("/listecomm")
	@ResponseBody
	public List<Comment>listedePosts(Authentication authentication){
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
	    User U = userRepository.findByUsername(U1.getUsername())
	            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		return ics.listeCommentaire(U.getId());
	}
	
	//commentaire par post ///ookk
		@GetMapping("/listecommpost")
		@ResponseBody
		List<Comment>listedeCommentairesp(@RequestParam("idPost") Long idPost){
			return ics.listeCommentairep(idPost);
		}
		//commentaire par post ///ookk
				@GetMapping("/listecommpost/{idPost}")
				@ResponseBody
				List<Comment>listedeCommentairespangular(@PathVariable("idPost") Long idPost){
					return ics.listeCommentairep(idPost);
				}
		////ook
		@PostMapping("/ajoutersouscomm")
		public void ajouterSousCom( @RequestBody Comment c ,Authentication authentication, @RequestParam("idCom")  Long idCom) {
			UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
		    User U = userRepository.findByUsername(U1.getUsername())
		            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
			ics.addSousCommentaire(c, U.getId(), idCom);
		}
	///ook	
		@GetMapping("/listesouscomm")
		@ResponseBody
		List<Comment>listedesousCommentair(@RequestParam("idCom") Long idCom){
			return ics.listeSousComment(idCom);
		}
		/////angular
		///ook	
				@GetMapping("/listesouscommm/{idCom}")
				@ResponseBody
				List<Comment>listedesousCommentaire(@PathVariable("idCom") Long idCom){
					return ics.listeSousComment(idCom);
				}
		////
		/////commparpost user///mazelit
		@GetMapping("/listecommbypostuser/{postId}")
		 public List<Comment>findCommentairebySujetAndUser(Authentication authentication,@PathVariable(value = "postId") Long postId){
			UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
		    User U = userRepository.findByUsername(U1.getUsername())
		            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
			List<Comment> com=new ArrayList<>();
			com=ics.getCommentaire(postId, U.getId());
		return com;
		} 
		///////count comm/user
		@GetMapping("/countcommbyuser")
		@ResponseBody
		public int countComByUser(@RequestParam("idUser") Long idUser){
			return ics.countComByUser(idUser);
		}
	///////count comm/post
			@GetMapping("/countcommbypost")
			@ResponseBody
			public int countComByPost(@RequestParam("idPost") Long idPost){
				return ics.countComByPost(idPost);
			}
			
			/////angularrrr test
			@PostMapping("/ajoutercommangular")
			public ResponseEntity<?> ajouterComAngular( @RequestBody Comment c, @RequestParam("idPost")  Long idPost) {
				
				ics.addCommentaireAngular( c, idPost);
				return 	ResponseEntity.ok().body(c);
			}
		////angularr test2
			@PostMapping("/ajoutersouscommangular")
			public void ajouterSousCom( @RequestBody Comment c , @RequestParam("idCom")  Long idCom) {
				
				ics.addSousCommentaireAngular(c, idCom);
			}
			//get comm by id
			//ok
			@RequestMapping(value = "/getcomm/{id}")
			public Comment getComm(@PathVariable Long id) {
			
				Comment c= ics.getCommentaire(id);
						return c;
			}	
			//ok
			@RequestMapping(value = "/getusercomm/{id}")
			public User getUserComm(@PathVariable Long id) {
			
				User c= ics.getUserComm(id);
				System.out.println("user comm  "+c.getId());
						return c;
			}
}
