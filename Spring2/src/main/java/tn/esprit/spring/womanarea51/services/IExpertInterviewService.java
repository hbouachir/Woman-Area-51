package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.ExpertInterview;
import tn.esprit.spring.womanarea51.entities.User;

public interface IExpertInterviewService {

	ExpertInterview addExpertInterview(ExpertInterview exp);
	ExpertInterview showExpertInterview(Long idExpertInterview);
	 List<ExpertInterview> showAllExpertInterview();
	 ExpertInterview UpdateExpertInterview (ExpertInterview exp);
	 void deleteExpertInterview (Long idExpertInterview);
	 List<User> searchExperts(List<User> users);
	 User ExpertToAffect(List<User> experts,String field);
	 int countInterviews(User expert);
	 int countAllInterviews();
}
