package tn.esprit.spring.womanarea51.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.payload.request.SignupRequest;
import tn.esprit.spring.womanarea51.payload.response.MessageResponse;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.UserService;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TestController {
	@Autowired
    UserService userService;
	
	@Autowired
	UserRepository userRepository;
	




	
	@PutMapping("/disableUser/{username}")
	@PreAuthorize("hasRole('ROLE_SUPER_USER')")
	public ResponseEntity<?> DisableUser(@PathVariable(value = "username") String username) {
		
		if (!userRepository.existsByUsername(username)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username not found!"));
		}
		
		User U = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		U.setEtatAcc(false);
		
		userService.updateUser(U);
		
		return ResponseEntity.ok(new MessageResponse("User Disabled successfully!"));
	}

}
