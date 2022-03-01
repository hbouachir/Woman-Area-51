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

import tn.esprit.spring.entities.ExpertInterview;
import tn.esprit.spring.services.IExpertInterviewService;
@RestController

public class ExpertInterviewController {
	@Autowired
	IExpertInterviewService exp ;
	@PostMapping("/addExpertInterview")
	public ExpertInterview addExpertInterview(@RequestBody ExpertInterview ei){
		return exp.addExpertInterview(ei);
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
