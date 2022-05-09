package tn.esprit.spring.womanarea51.services;

import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import tn.esprit.spring.womanarea51.entities.Contract;
import tn.esprit.spring.womanarea51.entities.Interview;
import tn.esprit.spring.womanarea51.entities.User;


public interface InterviewService {
	List<Interview> showAllInterview();
	public void PostToOffer(Long idOffer, Long userId);
	public void addInterview(Interview i ,Long idOffer, Long userId);
	public void UpdateInterview(Interview i ,Long idOffer, Long userId);
	List<User> ListOfAcceptedUser();
	List<User> ListOfPendingUser();
	List<User> ListOfRejectedUser();
	List<Interview> listInterviewsParUser (Long userId );
	void rejectUser(Interview i);
	void PendUser(Interview i);
	void generateContract(Interview i);
	void sendMail(String toEmail,String body,String subject,String attachment)throws MessagingException;
	void sendMailNoAttachement(String toEmail,String body,String subject)throws MessagingException;
	List<Contract> ContractParUser (Long userId );
	void DelteInterview(Long idOffer, Long userId);
	List<Contract> showAllContract();
	Interview showOneInterview(Long idOffer, Long userId);

}