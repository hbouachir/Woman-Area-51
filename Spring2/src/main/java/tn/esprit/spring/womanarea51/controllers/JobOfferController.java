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
import tn.esprit.spring.womanarea51.entities.JobOffer;
import tn.esprit.spring.womanarea51.services.IJobOfferService;

@RestController
public class JobOfferController {

	@Autowired
	IJobOfferService js ;
	@PostMapping("/addOffer")
	public JobOffer addOffer(@RequestBody JobOffer j){
		return js.addOffer(j);
	}
	@DeleteMapping("/deleteOffer/{idOffer}")
	public void deleteOffer(@PathVariable long idOffer){
		js.deleteOffer(idOffer);
	}
	@PutMapping("/updateOffer")
	public JobOffer updateOffer(@RequestBody JobOffer j){
		return js.updateJobOffer(j);
		}
	@GetMapping("/getOneOffer/{idOffer}")
	public JobOffer showOffer(@PathVariable long idOffer ){
		return js.showOneOffer(idOffer);	
	}
	@GetMapping("/ShowAllOffer")
	public List<JobOffer> showAllOffer(){	
		return js.showAllOffer();			
	}
}
