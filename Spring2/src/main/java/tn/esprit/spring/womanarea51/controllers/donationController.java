package tn.esprit.spring.womanarea51.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.donation;
import tn.esprit.spring.womanarea51.entities.fund;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IDonationService;
import tn.esprit.spring.womanarea51.services.IEmailingService;
import tn.esprit.spring.womanarea51.services.IFundService;
import tn.esprit.spring.womanarea51.services.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class donationController {
	
	@Autowired
	IDonationService IDS;
	@Autowired
	IFundService IFS;
	@Autowired
	IUserService IUS;
	@Autowired
	UserRepository UR;
	@Autowired
	IEmailingService IEmailS;
	
	
	//donate with credit card
	@PostMapping("/Donate/{fundId}")
	
	void Donate( Authentication authentication, @RequestBody donation d, @PathVariable("fundId")Long fundId ) throws StripeException, Exception {
//		System.out.println("testing donation function");
//		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
//		User U = UR.findByUsername(U1.getUsername())
//		        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));

		User U=UR.findByUsername(authentication.getName()).orElse(null);
		fund f=IFS.FindFund(fundId);
		System.out.println(f.toString());
		d.setFund(f);
		d.setUser(U);
		d.setConfirmed(true);
		
		IDS.AddDonation(d);
		
		f.setRaised(f.getRaised()+d.getAmount());
		Date date=new Date();
		f.setLastDonation(date);
		IFS.EditFund(f);
		
	}
	
	
	//donate with cheque or cash
	@PostMapping("/DonateSymbolic/{fundId}")
	
	public void SymbolicDonate(Authentication authentication, @RequestBody donation d, @PathVariable("fundId")Long fundId) throws Exception {
		User U=UR.findByUsername(authentication.getName()).orElse(null);
		fund f=IFS.FindFund(fundId);
		System.out.println(f.toString());
		d.setFund(f);
		d.setUser(U);
		d.setConfirmed(false);
		IDS.EditDonation(d);	
	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/Donation/confirm/{id}")
	donation Confirmdonation(Authentication authentication,@PathVariable("id") Long id) throws Exception {
		donation d=IDS.FindDonation(id);
		fund f=IFS.FindFund(d.getFund().getFundId());
		f.setRaised(f.getRaised()+d.getAmount());
		d.setConfirmed(true);
		Date date=new Date();
		f.setLastDonation(date);
		IFS.EditFund(f);
		IEmailS.confirmdonation(d.getUser(), d);
		return IDS.EditDonation(d);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/Donation/Update")
	donation EditDonation( Authentication authentication, @RequestBody donation d) {
		
		return IDS.EditDonation(d);
				
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/remove-donation/{donationId}")
	
	void RemoveDonation(Authentication authentication, @PathVariable("donationId") Long donationId) {
		donation d=IDS.FindDonation(donationId);
		IDS.DeleteDonation(d);
	}
	
	
	@GetMapping("/find-donations-by-user/{id-user}")
	
	List<donation> FindUserDonations(@PathVariable("id-user") Long userId) {
		User user=IUS.findOne(userId);
		return IDS.FindDonationsByUser(user);
	}
	
	
	@GetMapping("/find-donations-by-fund/{id-fund}")
	
	List<donation> FindFundDonations(@PathVariable("id-fund") Long fundId) {
		return IDS.FindDonationsByFund(fundId);
	}
	
	
	@GetMapping("/find-all-donations")
	
	List<donation> FindAllDonations() {
		return IDS.ListDonations();
	}
	

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/donation/{status}")
	List<donation>ListByStatus(@PathVariable("status")Boolean s){
		return IDS.ListConfirmedStatus(s);
		
	}
	@GetMapping("/donation/getUser/{id}")
	User GetUser(@PathVariable("id")Long id) {
		return IDS.FindDonation(id).getUser();
	}
}
