package tn.esprit.spring.womanarea51.controllers;

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

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.donation;
import tn.esprit.spring.womanarea51.entities.fund;
import tn.esprit.spring.womanarea51.services.IDonationService;
import tn.esprit.spring.womanarea51.services.IFundService;
import tn.esprit.spring.womanarea51.services.IUserService;

@RestController
public class donationController {
	
	@Autowired
	IDonationService IDS;
	@Autowired
	IFundService IFS;
	@Autowired
	IUserService IUS;
	
	@PostMapping("/Donate/{idFund}/{id-user}")
	@ResponseBody
	void Donate(@RequestBody donation d, @PathVariable ("idFund") Long idFund, @PathVariable ("id-user") Long idUser) {
		fund f=IFS.FindFund(idFund);
		d.setFund(f);
		d.setUser(IUS.findOne(idUser));
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
	
	
	@GetMapping("/find-donations-by-user/{id-user}")
	@ResponseBody
	List<donation> FindUserDonations(@PathVariable("id-user") Long userId) {
		User user=IUS.findOne(userId);
		return IDS.FindDonationsByUser(user);
	}
	
	
	@GetMapping("/find-donations-by-fund/{id-fund}")
	@ResponseBody
	List<donation> FindFundDonations(@PathVariable("id-fund") Long fundId) {
		return IDS.FindDonationsByFund(fundId);
	}
	
	
	@GetMapping("/find-all-donations")
	@ResponseBody
	List<donation> FindAllDonations() {
		return IDS.ListDonations();
	}
	

}
