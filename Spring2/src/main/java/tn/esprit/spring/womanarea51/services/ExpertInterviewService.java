package tn.esprit.spring.womanarea51.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.ExpertInterview;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.ExpertInterviewRepository;
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
	@Override
	public List<User> searchExperts(List<User> users) {
		List<User> experts = new ArrayList<User>();
		for (int i=0;i<users.size();i++){
			if(users.get(i).getRole().getDescription().contains("expert"))
				experts.add(users.get(i));
			
		}
		return experts;
	}
	
	/*@Override
	public User BestExpert(List<User> experts, String field) {
		User expert = new User();
		for (int i=0;i<experts.size();i++){
			if(experts.get(i).getRole().getExpertField().contains(field)&&this.countInterviews(experts.get(i))<=3)
			{
				Set<ExpertInterview> expint=experts.get(i).getExpertinterviews();
				for (ExpertInterview element : expint) {
					int total=+element.getRating();	
						
						
				}
				}
			}
		}		
		return expert;
	}*/
	
	@Override
	public User ExpertToAffect(List<User> experts, String field) {
		User expert = new User();
		for (int i=0;i<experts.size();i++){
			if(experts.get(i).getRole().getExpertField().contains(field)&&this.countInterviews(experts.get(i))<=2){
				return expert=experts.get(i);
			}
		}		
		return expert;
	}
	
	@Override
	public int countInterviews(User expert) {
		List<ExpertInterview> intss=new ArrayList<ExpertInterview>();
		intss=( List<ExpertInterview>) exp.findAll();
		int n=0;
		for (int i=0;i<intss.size();i++){
			
			 if (intss.get(i).getExpert().getUserId()==expert.getUserId())
				n++;
			 
		}
		
		//ints.addAll(0, expert.getExpertinterviews());
		return n;
	}
	@Override
	public int countAllInterviews(){
		int n=0;
		
		return n;
	}
}
