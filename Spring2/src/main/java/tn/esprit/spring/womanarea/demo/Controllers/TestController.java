package tn.esprit.spring.womanarea.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.womanarea.demo.Entities.User;
import tn.esprit.spring.womanarea.demo.Payload.request.SignupRequest;
import tn.esprit.spring.womanarea.demo.Payload.response.MessageResponse;
import tn.esprit.spring.womanarea.demo.Repositories.UserRepository;
import tn.esprit.spring.womanarea.demo.Services.UserService;
import tn.esprit.spring.womanarea.demo.security.services.UserDetailsImpl;

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
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {

		return "User Content.";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    public UserDetailsImpl currentUserName(Authentication authentication) {
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
       // return userDetails.getAddress();
		// return userDetails.getId(); //LONG
	    return userDetails;

    }

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	@GetMapping("/afficherUsers")
	public List<User> getAllUser() {
		return userService.findAll();
	}
	
	@PutMapping("/disableUser/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> DisableUser(@PathVariable(value = "username") String username,@Valid @RequestBody SignupRequest signUpRequest) {
		
		if (!userRepository.existsByUsername(signUpRequest.getUsername())) {
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
