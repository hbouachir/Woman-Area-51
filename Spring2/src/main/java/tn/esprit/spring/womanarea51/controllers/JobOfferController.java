package tn.esprit.spring.womanarea51.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.JobOffer;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.JobOfferService;

@RestController
public class JobOfferController {
@Autowired
JobOfferService js ;
@Autowired
UserRepository userRepository;


@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/addOffer")
public JobOffer addOffer(@RequestBody JobOffer j){
	return js.addOffer(j);
}
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/deleteOffer/{idOffer}")
public void deleteOffer(@PathVariable long idOffer){
	js.deleteOffer(idOffer);
}
@PreAuthorize("hasRole('ADMIN')")
@PutMapping("/updateOffer/{idOffer}")
public JobOffer updateOffer(@PathVariable ("idOffer") long idOffer,@RequestBody JobOffer jo){
	return js.updateJobOffer(jo, idOffer);
	}
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/getOneOffer/{idOffer}")
public JobOffer showOffer(@PathVariable long idOffer ){
	return js.showOneOffer(idOffer);
}
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@GetMapping("/ShowAllOffer")
public List<JobOffer> showAllOffer(){	
	return js.showAllOffer();			
}
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@GetMapping("/JobOfferByDomaine/{domaine}")
public List<JobOffer> searchOfferByDomaine(@PathVariable("domaine")String domaine) {
	return js.searchOfferByDomaine(domaine);
}
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@GetMapping("/JobOfferByPlace/{offerPlace}")
public List<JobOffer> searchOfferByPlace(@PathVariable("offerPlace")String offerPlace) {
	return js.searchOfferByPlace(offerPlace);
}
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@GetMapping("/JobOfferMotCle")
public List<JobOffer> searchOfferMotCle(@RequestParam ("mc")String mc) {
	return js.searchOfferMocCle("%"+mc+"%");
}


}
