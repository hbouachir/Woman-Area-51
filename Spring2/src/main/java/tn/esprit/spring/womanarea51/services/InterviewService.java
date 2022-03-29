package tn.esprit.spring.womanarea51.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.Interview;
import tn.esprit.spring.womanarea51.repositories.InterviewRepository;

@Service
public class InterviewService implements IInterviewService {

	@Autowired
	InterviewRepository ir ;
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
	@Override
	public Interview showInterview(Long idInterview) {
		// TODO Auto-generated method stub
		return ir.findById(idInterview).orElse(null);
	}

}
