package tn.esprit.spring.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.JobOffer;
import tn.esprit.spring.services.JobOfferService;

@RestController
public class JobOfferController {
@Autowired
JobOfferService js ;

@PostMapping("/addOffer")
public JobOffer addOffer(@RequestBody JobOffer j){
	return js.addOffer(j);
}
@DeleteMapping("/deleteOffer/{idOffer}")
public void deleteOffer(@PathVariable long idOffer){
	js.deleteOffer(idOffer);
}
@PutMapping("/updateOffer/{idOffer}")
public JobOffer updateOffer(@PathVariable ("idOffer") long idOffer,@RequestBody JobOffer jo){
	return js.updateJobOffer(jo, idOffer);
	}
@GetMapping("/getOneOffer/{idOffer}")
public JobOffer showOffer(@PathVariable long idOffer ){
	return js.showOneOffer(idOffer);
}
@GetMapping("/ShowAllOffer")
public List<JobOffer> showAllOffer(){
	return js.showAllOffer();
}
@GetMapping("/JobOfferByDomaine/{domaine}")
public List<JobOffer> searchOfferByDomaine(@PathVariable("domaine")String domaine) {
	return js.searchOfferByDomaine(domaine);
}
@GetMapping("/JobOfferByPlace/{offerPlace}")
public List<JobOffer> searchOfferByPlace(@PathVariable("offerPlace")String offerPlace) {
	return js.searchOfferByPlace(offerPlace);
}
@GetMapping("/JobOfferMotCle")
public List<JobOffer> searchOfferMotCle(@RequestParam ("mc")String mc) {
	return js.searchOfferMocCle("%"+mc+"%");
}


}
