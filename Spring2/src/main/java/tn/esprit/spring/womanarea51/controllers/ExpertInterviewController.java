package tn.esprit.spring.womanarea51.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.ExpertInterview;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.ExpertInterviewRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;
import tn.esprit.spring.womanarea51.services.IExpertInterviewService;
import tn.esprit.spring.womanarea51.services.IUserService;
@RestController
public class ExpertInterviewController {
	@Autowired
	IExpertInterviewService exp ;
	@Autowired
	IUserService userservice;
	@Autowired
	ExpertInterviewRepository exrep;
	@Autowired
	UserRepository urep;
	
	
	
	
	/*@PostMapping("/retrieveRatings")
	public ExpertInterview retrieveRatings(@RequestBody ExpertInterview ei,Authentication authentication){	
		
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
        User U = urep.findByUsername(U1.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		//System.out.println(userservice.ShowAllUser().toString());
		List<Integer> intss=new ArrayList<Integer>();
		intss=exrep.retrieveRatings();
		/*for(int i=0;i<intss.size();i++){
			
			if (exrep.countInterviews(userservice.findOne(intss.get(i)).getId())<=2
					&&userservice.findOne(intss.get(i)).getRole().getExpertField().contains(ei.getExpertField())){
				ei.setExpert(userservice.findOne(intss.get(i)));
				ei.setUser(userservice.findOne(iduser));
				return exp.addExpertInterview(ei);
				//break;
			}

		}
		//exrep.retrieveRatings().get(index);
		return this.addExpertInterview(ei, U.getId());			
	}
*/
	
	@PostMapping("/addExpertInterview")
	public ExpertInterview addExpertInterview(@RequestBody ExpertInterview ei, Authentication authentication){

		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
        User U = urep.findByUsername(U1.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
        
		List<User> users,experts;
		User expert = new User();
		//users=userservice.findAll();
		//uss=userservice.ShowAllUser();
		//System.out.println("\n users:"+userservice.ShowAllUser().toString());
		//experts=exp.searchExperts(users);
		//expert=exp.ExpertToAffect(experts,ei.getExpertField());
		//System.out.println("\n experts:"+experts.toString());
		
		
			//System.out.println(expert.getFirstName());
			//if(exp.countInterviews(expert)<=2){
				//ei.setExpert(expert);
				ei.setUser(userservice.findOne(U.getId()));
				exp.addExpertInterview(ei);
				System.out.println("\n Added");
			
			
				System.out.println("Cannot add more interviews");
			
		//}else System.out.println("Null");
		      //exp.addExpertInterview(ei);
		System.out.println("fin controller");
		//return exp.ExpertToAffect(experts,ei.getExpertField());*/
		return null;
	}
	
	@GetMapping("/test1")
	public List<User> test1(){	
		//System.out.println(userservice.ShowAllUser().toString());
		return userservice.findAll();			
	}
	@PostMapping("/test2")
	public String test2(@RequestBody ExpertInterview ei){	
		//List<User> users= userservice.ShowAllUser();	
		//System.out.println(users.toString());
		return ei.getExpertField();
	}
	
	
	
	@DeleteMapping("/deleteExpertInterview/{idExpertInterview}")
	public void deleteExpertInterview(@PathVariable long idExpertInterview){
		exp.deleteExpertInterview(idExpertInterview);
	}
	@PutMapping("/updateExpertInterview")
	public ExpertInterview updateExpertInterview(@RequestBody ExpertInterview ei){
		return exp.UpdateExpertInterview(ei);
		}
	@GetMapping("/getOneExpertInterview/{idExpertInterview}")
	public ExpertInterview showExpertInterview(@PathVariable long idExpertInterview ){
		return exp.showExpertInterview(idExpertInterview)	;	
	}
	@GetMapping("/ShowAllExpertInterview")
	public List<ExpertInterview> showAllExpertInterview(){	
		return exp.showAllExpertInterview();			
	}
}
