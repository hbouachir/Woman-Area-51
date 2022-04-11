package tn.esprit.spring.womanarea51.services;

import javax.mail.MessagingException;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.donation;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.feedback;

public interface IEmailingService {

	public void CreditCardDonation(User U, donation d) throws MessagingException, Exception;
	
	public void confirmdonation(User U, donation d) throws Exception;
	
	public String GenerateBadge(User U, event e) throws Exception;
	
	public void ParticipationConfirmation(User U, event e,String pathPDF)throws Exception;
	
	public void feedbackConfirmation(feedback f,User U) throws Exception;
	
	public void CancelParticipation(feedback f) throws Exception;
}
