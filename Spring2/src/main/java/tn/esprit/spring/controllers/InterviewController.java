package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Interview;

import tn.esprit.spring.services.IInterviewService;

@RestController
public class InterviewController {

	
	@Autowired
	IInterviewService in ;
	@PostMapping("/addInterview")
	public Interview addInterview(@RequestBody Interview i){
		return in.addInterview(i);
	}
	@PostMapping("/add-Interviews/{idOffer}/{userId}")
	public void addlisInterviewsToCandidate (@RequestBody List<Interview> iv , @PathVariable Long idOffer,@PathVariable Long userId){
		in.addlisInterviews(iv, userId, idOffer);
	}
	@DeleteMapping("/deleteInterview/{idInterview}")
	public void deleteInterview(@PathVariable long idInterview){
		in.deleteInterview(idInterview);
	}
	@PutMapping("/updateInterview")
	public Interview updateUser(@RequestBody Interview i){
		return in.UpdateInterview(i);
		}
	@GetMapping("/getOneInterview/{idInterview}")
	public Interview showInterview(@PathVariable long idInterview ){
		return in.showInterview(idInterview)	;	
	}
	@GetMapping("/ShowAllInterview")
	public List<Interview> showAllInterview(){	
		return in.showAllInterview();			
	}
	@GetMapping("/interviewsParUser/{userId}")
	public List<Interview> interviewsParUser(@PathVariable ("userId")Long userId){
		return  in.listInterviewsParUser(userId);
	}
	@GetMapping("/interview/{idOffer}/{userId}")
	public List<Interview> listInterviewsParOfferUser(@PathVariable Long idOffer,@PathVariable Long userId){
		return in.listInterviewsParOfferAndUser(idOffer, userId) ;
	}
}
