package tn.esprit.spring.womanarea51.services;

import java.util.List;

import com.stripe.exception.StripeException;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.donation;

public interface IDonationService {

	List<donation> ListDonations();

	donation FindDonation(Long id);

	void DeleteDonation(donation d);

	donation EditDonation(donation d);

	void AddDonation(donation d) throws StripeException, Exception ;
	
	List<donation> FindDonationsByFund(Long id);
	
	List<donation>FindDonationsByUser(User u);

	public List<donation>ListConfirmedStatus(Boolean s);
	

}
