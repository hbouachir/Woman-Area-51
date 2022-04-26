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
	




	
	@PutMapping("/disableUser/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_USER')")
	public ResponseEntity<?> DisableUser(@PathVariable(value = "id") long id) {
		

		User U = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found : "  ));
		U.setEtatAcc(!U.getEtatAcc());
		
		userService.updateUser(U);
		if (!U.getEtatAcc())
		return ResponseEntity.ok(new MessageResponse("User Disabled successfully!"));
		else  return ResponseEntity.ok(new MessageResponse("User activated successfully!"));
	}

	@PutMapping("/enableUser/{id}")
	@PreAuthorize("hasRole('ROLE_SUPER_USER')")
	public ResponseEntity<?> enableUser(@PathVariable(value = "id") long id) {


		User U = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found : "  ));
		U.setEnabled(!U.isEnabled());

		userService.updateUser(U);
		if (U.isEnabled())
			return ResponseEntity.ok(new MessageResponse("User enabled successfully!"));
		else  return ResponseEntity.ok(new MessageResponse("User not enabled successfully!"));
	}

}
