package tn.esprit.spring.womanarea51.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import tn.esprit.spring.womanarea51.entities.Complaint;
import tn.esprit.spring.womanarea51.entities.EExpertField;
import tn.esprit.spring.womanarea51.entities.ExpertInterview;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.ExpertInterviewRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;
import tn.esprit.spring.womanarea51.services.IExpertInterviewService;
import tn.esprit.spring.womanarea51.services.IUserService;

@RestController
@CrossOrigin
public class ExpertInterviewController {
	@Autowired
	IExpertInterviewService exp;
	@Autowired
	IUserService userservice;
	@Autowired
	ExpertInterviewRepository exrep;
	@Autowired
	UserRepository urep;
	@Autowired
	GoogleCalController calcont;
	
	
	
	@PostMapping("/rateinterview")
	public ExpertInterview rateInterview(@RequestBody ExpertInterview ei, Authentication authentication){
		System.out.println(ei.toString());
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
        User U = urep.findByUsername(U1.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		
        
		
		
		return exp.addExpertInterview(ei);
	}
	
	@PostMapping("/editinterview")
	public ExpertInterview editInterview(@RequestBody ExpertInterview ei, Authentication authentication){
		System.out.println(ei.toString());
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
        User U = urep.findByUsername(U1.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		
        
		
		
		return exp.addExpertInterview(ei);
	}

	@PostMapping("/addExpertInterview")
	public String addExpertInterview(@RequestBody ExpertInterview ei, Authentication authentication) throws IOException {
		System.out.println(ei.toString());
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
		User U = urep.findByUsername(U1.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));

		ei.setUser(userservice.findOne(U.getId()));
		//SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(ei.getExpertField().name());

		Date date = ei.getDateExpertInterview();
		String day = new SimpleDateFormat("EEEE").format(date);
		if (day.contains("samedi") || day.contains("dimanche")) { 
			return "cannot add interviews on " + day + "'s";
		}
		
		
		List<Integer> intss=new ArrayList<Integer>();
		String rolls=null;
		intss=exrep.retrieveRatings();
		System.out.println(intss.size());
		for(int i=0;i<=intss.size()-1;i++){
			rolls=null;
			System.out.println(i);
			
			
			rolls=exrep.getRoleField(exrep.getRoles(userservice.findOne(intss.get(i)).getId()));
			//roles=roles.concat(rolls);
			System.out.println(rolls);
			System.out.println(ei.getExpertField().name());
			

			if (exrep.countInterviews(userservice.findOne(intss.get(i)).getId())<=5
					&&rolls.contains(ei.getExpertField().name())){
				
						
				ei.setExpert(userservice.findOne(intss.get(i)));
				ei.setUser(userservice.findOne(U.getId()));
						if(ei.getInterviewType()==true){
							SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
							//String date=DateFor.format(inter.getDateExpertInterview());
							calcont.addeventtest(DateFor.format(ei.getDateExpertInterview()),U.getEmail());
							//return date;
							}
						exp.addExpertInterview(ei);
						return rolls;
						
						
				
				
					
				
				
			}
			//exint.UpdateExpertInterview(inter);
			//return rolls.toString()+" with id: "+roles;
			//return rolls+" with id: "+exrep.getRoles(userservice.findOne(intss.get(i)).getId());
			//return intss.toString()+rolls+" with id: "+exrep.getRoles(userservice.findOne(intss.get(i)).getId());

		}
		
		// expint=null;
		// System.out.println("Date : "+date);
		
		System.out.println("\n Added");

		System.out.println("Cannot add more interviews");

		// }else System.out.println("Null");
		// exp.addExpertInterview(ei);
		System.out.println("fin controller");
		// return exp.ExpertToAffect(experts,ei.getExpertField());*/
		return null;
	}

	@GetMapping("/test1")
	public List<User> test1() {
		// System.out.println(userservice.ShowAllUser().toString());
		return userservice.findAll();
	}

	@PostMapping("/test2")
	public EExpertField test2(@RequestBody ExpertInterview ei) {
		// List<User> users= userservice.ShowAllUser();
		// System.out.println(users.toString());
		return ei.getExpertField();
	}

	@DeleteMapping("/deleteExpertInterview/{idExpertInterview}")
	public void deleteExpertInterview(@PathVariable long idExpertInterview) {
		exp.deleteExpertInterview(idExpertInterview);
	}

	@PutMapping("/updateExpertInterview")
	public ExpertInterview updateExpertInterview(@RequestBody ExpertInterview ei) {
		return exp.UpdateExpertInterview(ei);
	}

	@GetMapping("/getOneExpertInterview/{idExpertInterview}")
	public ExpertInterview showExpertInterview(@PathVariable long idExpertInterview) {
		return exp.showExpertInterview(idExpertInterview);
	}

	@GetMapping("/ShowAllExpertInterview")
	public List<ExpertInterview> showAllExpertInterview() {
		return exp.showAllExpertInterview();
	}
	
	@GetMapping("/getBestExperts")
	public List<User> getBestExperts() {
		int i=0;
		List<User> list=new ArrayList<User>();
		List<Integer> listn=new ArrayList<Integer>();
		listn=exrep.retrieveRatings();
		for(i=0;i<=listn.size()-1;i++){
			list.add(userservice.findOne(listn.get(i)));
		}
		
		return list;
	}
	@GetMapping("/getBestExpertsRatings")
	public List<User> getBestExpertsRatings(@RequestBody User u) {
		int i=0;
		List<User> list=new ArrayList<User>();
		List<Integer> listn=new ArrayList<Integer>();
		listn=exrep.retrieveRatings();
		for(i=0;i<=listn.size()-1;i++){
			list.add(userservice.findOne(listn.get(i)));
		}
		
		return list;
	}
	
	
}
