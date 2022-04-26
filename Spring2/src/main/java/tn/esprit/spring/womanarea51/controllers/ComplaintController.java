package tn.esprit.spring.womanarea51.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.Complaint;
import tn.esprit.spring.womanarea51.entities.ComplaintType;
import tn.esprit.spring.womanarea51.services.IComplaintService;
@RestController
public class ComplaintController {
	@Autowired
	IComplaintService comp ;
	@PostMapping("/addComplaint")
	public String addComplaint(@RequestBody Complaint c){
		String resp = null;
		if(c.getDescription().contains("fight")||c.getDescription().contains("harrassment")
				||c.getDescription().contains("pain")){
			//c.setComplaintType(ComplaintType.Help);
			return "You might wanna add a file attachment";
			
		}
			else if(c.getDescription().contains("money"))
					c.setComplaintType(ComplaintType.financialComplaint);
		
		//c.setComplaintType(ComplaintType.Help);
		return resp;
	}
	@DeleteMapping("/deleteComplaint/{idComplaint}")
	public void deleteComplaint(@PathVariable long idComplaint){
		comp.deleteComplaint(idComplaint);
	}
	@PutMapping("/updateComplaint")
	public Complaint updateComplaint(@RequestBody Complaint c){
		return comp.UpdateComplaint(c);
		}
	@GetMapping("/getOneComplaint/{idComplaint}")
	public Complaint showComplaint(@PathVariable long idComplaint ){
		return comp.showComplaint(idComplaint)	;	
	}
	@GetMapping("/ShowAllComplaint")
	public List<Complaint> showAllComplaint(){	
		return comp.showAllComplaint();			
	}
	
	
	
	
	
	
	
}
