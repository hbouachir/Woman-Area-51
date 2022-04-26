package tn.esprit.spring.services;

import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contract;
import tn.esprit.spring.entities.Interview;


public interface InterviewService {
	List<Interview> showAllInterview();
	public void PostToOffer(Long idOffer, Long userId);
    public void addInterview(Interview i ,Long idOffer, Long userId);
    public void UpdateInterview(Interview i ,Long idOffer, Long userId);
    Set<String> ListOfAcceptedUser();
    Set<String> ListOfPendingUser();
    Set<String> ListOfRejectedUser();
    List<Interview> listInterviewsParUser (Long userId );
    void rejectUser(Interview i);
    void PendUser(Interview i);
    void generateContract(Interview i);
    void sendMail(String toEmail,String body,String subject,String attachment)throws MessagingException;
    List<Contract> ContractParUser (Long userId );
    void DelteInterview(Long idOffer, Long userId);

}
