package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.donation;
import tn.esprit.spring.entities.fund;
import tn.esprit.spring.services.IDonationService;
import tn.esprit.spring.services.IFundService;

@RestController
public class donationController {
	
	@Autowired
	IDonationService IDS;
	@Autowired
	IFundService IFS;
	
	@PostMapping("/Donate/{idFund}")
	@ResponseBody
	void Donate(@RequestBody donation d, @PathVariable ("idFund") Long idFund) {
		fund f=IFS.FindFund(idFund);
		d.setFund(f);
		IDS.AddDonation(d);
		f.setRaised(f.getRaised()+d.getAmount());
		IFS.EditFund(f);
		
	}
	
	@PutMapping("/Donation/Update")
	donation EditDonation(@RequestBody donation d) {
		
		return IDS.EditDonation(d);
				
	}
	
	@DeleteMapping("/remove-donation/{donationId}")
	@ResponseBody
	void RemoveDonation(@PathVariable("donationId") Long donationId) {
		donation d=IDS.FindDonation(donationId);
		IDS.DeleteDonation(d);
	}
	
	
/*	@GetMapping("/find-donations-by-user/{id-user}")
	List<donation> FindUserDonations(@PathVariable("id-user") Long userId) {
		return IDS.FindDonationsByUser(userId);
	}
*/	
	
	@GetMapping("/find-donations-by-fund/{id-fund}")
	List<donation> FindFundDonations(@PathVariable("id-fund") Long fundId) {
		return IDS.FindDonationsByFund(fundId);
	}
	
	
	@GetMapping("/find-all-donations")
	List<donation> FindAllDonations() {
		return IDS.ListDonations();
	}
	

}
