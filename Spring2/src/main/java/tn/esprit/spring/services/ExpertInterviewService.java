package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.ExpertInterview;
import tn.esprit.spring.repositories.ExpertInterviewRepository;
@Service
public class ExpertInterviewService implements IExpertInterviewService {
	@Autowired
	ExpertInterviewRepository exp ;
	@Override
	public ExpertInterview addExpertInterview(ExpertInterview ei) {
		// TODO Auto-generated method stub
		return exp.save(ei);
	}
	@Override
	public List<ExpertInterview> showAllExpertInterview() {
		// TODO Auto-generated method stub
		return ( List<ExpertInterview>) exp.findAll() ;
	}
	@Override
	public ExpertInterview UpdateExpertInterview(ExpertInterview ei) {
		// TODO Auto-generated method stub
		return exp.save(ei);
	}
	@Override
	public void deleteExpertInterview(Long idExpertInterview) {
		// TODO Auto-generated method stub
		exp.deleteById(idExpertInterview);
	}
	@Override
	public ExpertInterview showExpertInterview(Long idExpertInterview) {
		// TODO Auto-generated method stub
		return exp.findById(idExpertInterview).orElse(null);
	}
}
