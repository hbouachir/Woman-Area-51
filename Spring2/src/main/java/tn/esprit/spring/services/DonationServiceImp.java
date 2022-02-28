package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.donation;
import tn.esprit.spring.repositories.DonationRepository;

@Service
public class DonationServiceImp implements IDonationService {
	
	@Autowired
	DonationRepository DRepository;
	
	
	 
	public void AddDonation(donation d) {
		DRepository.save(d);
	}
	
	
	
	
	public donation EditDonation(donation d) {
		donation updated = new donation();
		updated=DRepository.save(d);
		return updated;
		
	}
	
	
	
	 
	public void DeleteDonation(donation d) {
		DRepository.delete(d);
	}
	
	
	
	
/*	public List<donation> FindDonationsByUser(Long id) {
		List<donation> list=new ArrayList<donation>() ;
		DRepository.findAll().forEach(
				d->{
					if (d.getUser().getUserId()==id)
						list.add(d);	
				});
				
		return list;
	}*/
	
	
	public List<donation> FindDonationsByFund(Long id){
		List<donation> list=new ArrayList<donation>() ;
		DRepository.findAll().forEach(
				d->{
					if (d.getFund().getFundId()==id)
						list.add(d);	
				});
				
		return list;
	}
	
	
	
	public donation FindDonation(Long id) {
		donation d=DRepository.findById(id).get();
		return d;
	}


	public List<donation> ListDonations() {
		List<donation> list=new ArrayList<donation>() ;
		DRepository.findAll().forEach(d->list.add(d));
		return list;
	}







	
}
