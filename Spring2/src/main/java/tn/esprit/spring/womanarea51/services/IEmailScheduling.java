package tn.esprit.spring.womanarea51.services;

import org.springframework.http.ResponseEntity;

import tn.esprit.spring.womanarea51.entities.EmailResponse;
import tn.esprit.spring.womanarea51.entities.event;

public interface IEmailScheduling {
	
	public ResponseEntity<EmailResponse> scheduleEmail(String email,String username, event e);

}
