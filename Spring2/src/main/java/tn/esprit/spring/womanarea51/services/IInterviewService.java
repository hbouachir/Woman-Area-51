package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.Interview;

public interface IInterviewService {

	 Interview addInterview(Interview i);
	 //void addlisInterviews (List<Interview> iv ,Long userId, Long idOffer); 
	 Interview showInterview(Long idInterview);
	 List<Interview> showAllInterview();
	 // List<Interview> listInterviewsParUser (Long userId);
	 //List<Interview> listInterviewsParOfferAndUser ( Long idOffer,Long userId );
	 Interview UpdateInterview (Interview i);
	 void deleteInterview (Long idInterview);

	 
}
