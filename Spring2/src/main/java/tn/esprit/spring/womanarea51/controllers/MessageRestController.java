package tn.esprit.spring.womanarea51.controllers;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.Mensaje;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;
import tn.esprit.spring.womanarea51.services.IMessageService;

@RestController
public class MessageRestController {
@Autowired 
IMessageService messageService;
@Autowired
UserRepository userRepository;

@GetMapping("/listemsgidid")
@ResponseBody
List<Mensaje> listedePostsanscom(Authentication authentication,@PathParam("idUser")Long idUser){
	UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
    User U = userRepository.findByUsername(U1.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
    System.out.println("id connecté"+U.getId());
	User U2= userRepository.findById(idUser).orElse(null);
	 System.out.println("id    "+U2.getId());
	return messageService.getAllMessages(U.getUsername(), U2.getUsername());
}
@DeleteMapping("/deleteconversation")
public String deleteChat(Authentication authentication,@PathParam("idUser")Long idUser){
	UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
    User U = userRepository.findByUsername(U1.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
    System.out.println("id connecté"+U.getId());
	User U2= userRepository.findById(idUser).orElse(null);
	 System.out.println("id    "+U2.getId());
	//List<Mensaje>listMsg= messageService.getAllMessages(U.getUsername(), U2.getUsername());
	messageService.delete(U.getUsername(), U2.getUsername());
	String msg="";
	msg="conversation supprimer avec succée";
	return msg;
}
}
