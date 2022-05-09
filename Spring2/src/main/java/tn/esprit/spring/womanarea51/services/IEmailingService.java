package tn.esprit.spring.womanarea51.services;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.mail.MessagingException;

import com.google.zxing.WriterException;

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
	
	public void DeleteBadgeFiles(User U, event e);
	
	public String GeneratgeBadgeByType(User U, event e,String type) throws IOException, WriterException, URISyntaxException;

	public void SpeakerMail(User U, event e, String pathPDF) throws Exception;
	
	public void StaffMail(User U, event e, String pathPDF) throws Exception;

	public void VirtualEvent(User U, event e) throws Exception ;

	public void eventUpdate(User U, event e,String pathPDF) throws Exception;
	
	public void Personalized(User U,String subject, String body) throws Exception;

}
