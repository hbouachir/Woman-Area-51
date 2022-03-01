package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.ExpertInterview;

public interface IExpertInterviewService {

	ExpertInterview addExpertInterview(ExpertInterview exp);
	ExpertInterview showExpertInterview(Long idExpertInterview);
	 List<ExpertInterview> showAllExpertInterview();
	 ExpertInterview UpdateExpertInterview (ExpertInterview exp);
	 void deleteExpertInterview (Long idExpertInterview);
}
