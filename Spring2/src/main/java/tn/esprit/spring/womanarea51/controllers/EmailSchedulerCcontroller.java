package tn.esprit.spring.womanarea51.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.womanarea51.entities.EmailResponse;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IEmailScheduling;
import tn.esprit.spring.womanarea51.services.IEventService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class EmailSchedulerCcontroller {
	
	@Autowired
	IEmailScheduling IEmailS;
	
	@Autowired
	UserRepository UR;
	
	@Autowired
	IEventService IES;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/schedule/email")
	public ResponseEntity<EmailResponse> scheduleEmail(@RequestBody event e,Authentication authentication){
		
		User U=UR.findByUsername(authentication.getName()).orElse(null);
		System.out.println(U.getEmail());
		
		return IEmailS.scheduleEmail(U.getEmail(),U.getUsername(),e);
	}
	
	
}
