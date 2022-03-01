package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Interview;
import tn.esprit.spring.entities.JobOffer;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.InterviewRepository;
import tn.esprit.spring.repositories.JobOfferRepository;
import tn.esprit.spring.repositories.UserRepository;

@Service
public class InterviewService implements IInterviewService {

	@Autowired
	InterviewRepository ir ;
	@Autowired
	UserRepository ur ;
	@Autowired
	JobOfferRepository jr ;
	@Override
	public Interview addInterview(Interview i) {
		// TODO Auto-generated method stub
		return ir.save(i);
	}
	@Override
	public List<Interview> showAllInterview() {
		// TODO Auto-generated method stub
		return ( List<Interview>) ir.findAll() ;
	}
	@Override
	public Interview UpdateInterview(Interview i) {
		// TODO Auto-generated method stub
		return ir.save(i);
	}
	@Override
	public void deleteInterview(Long idInterview) {
		// TODO Auto-generated method stub
		ir.deleteById(idInterview);
	}
	/*
	@Override
	public Interview showInterview(Long idInterview) {
		// TODO Auto-generated method stub
		return ir.findById(idInterview).orElse(null);
	}
	@Override
	public void addlisInterviews(List<Interview> iv, Long userId, Long idOffer) {
		User user = ur.findById(userId).orElse(null);
		JobOffer joboffer =  jr.findById(idOffer).orElse(null);		
			for (Interview interview : iv){	
			interview.setUser(user);;
			interview.setJobOffer(joboffer);
			ir.save(interview);
		} 
		
	} */
	@Override
	public List<Interview> listInterviewsParUser(Long userId) {
		List<Interview> interviewParUser =( List<Interview>) ir.listInterviewsParUser(userId);
		return interviewParUser ;
	}
	@Override
	public List<Interview> listInterviewsParOfferAndUser(Long idOffer, Long userId) {
		List<Interview> interviewParUserOffer =( List<Interview>) ir.listInterviewsParOfferUser(idOffer, userId);
		return interviewParUserOffer ;
	}
	@Override
	public void addlisInterviews(List<Interview> iv, Long userId, Long idOffer) {
		// TODO Auto-generated method stub
		User user = ur.findById(userId).orElse(null);
		JobOffer joboffer =  jr.findById(idOffer).orElse(null);		
			for (Interview interview : iv){	
			interview.setUser(user);;
			interview.setJobOffer(joboffer);
			ir.save(interview);
	}
	}
	@Override
	public Interview showInterview(Long idInterview) {
		// TODO Auto-generated method stub
		return ir.findById(idInterview).orElse(null);
	}

}
