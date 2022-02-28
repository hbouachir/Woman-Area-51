package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.donation;

public interface IDonationService {

	List<donation> ListDonations();

	donation FindDonation(Long id);

	void DeleteDonation(donation d);

	donation EditDonation(donation d);

	void AddDonation(donation d);
	
	List<donation> FindDonationsByFund(Long id);
	
//	List<donation>FindDonationsByUser(Long id);

	

}
