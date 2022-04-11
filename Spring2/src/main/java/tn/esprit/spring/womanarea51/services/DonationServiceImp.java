package tn.esprit.spring.womanarea51.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;


import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.donation;
import tn.esprit.spring.womanarea51.entities.fund;
import tn.esprit.spring.womanarea51.repositories.DonationRepository;
import tn.esprit.spring.womanarea51.repositories.FundRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.stripe.StripeService;

@Service
public class DonationServiceImp implements IDonationService {
	
	@Autowired
    DonationRepository DRepository;
	
	@Autowired
	StripeService stripeService;
	
	@Autowired 
	FundRepository FRepository;
	
	@Autowired
	UserRepository URepository;
	
	@Autowired
	EmailingServiceImp ES;
	
	//Donation with creditcard 
	public void AddDonation(donation d) throws StripeException, Exception {
		DRepository.save(d);
		User U=URepository.findById(d.getUser().getId()).get();
		if (U.getStripe_id()==null){
           stripeService.createCustomer(U.getUsername(),U.getEmail());
           U.setStripe_id(stripeService.createCustomer(U.getUsername(),U.getEmail()).getId());
            URepository.save(U);
           String customerId =U.getStripe_id();
           stripeService.createCard(customerId);


       }
        String customerId =U.getStripe_id();
        System.out.println(customerId);

        Charge c= stripeService.chargeCustomerCard(customerId,(int)d.getAmount());
		ES.CreditCardDonation(U, d);
        
		
	}

	
	
	public donation EditDonation(donation d) {
		donation updated = new donation();
		updated=DRepository.save(d);
		return updated;
		
	}
	
	
	
	 
	public void DeleteDonation(donation d) {
		DRepository.delete(d);
	}
	
	
	public List<donation> FindDonationsByUser(User u) {
		List<donation> list=new ArrayList<donation>() ;
		DRepository.findAll().forEach(
				d->{
					if (d.getUser()==u)
						list.add(d);	
				});
				
		return list;
	}
	
	
	public List<donation> FindDonationsByFund(Long id){
		List<donation> list=new ArrayList<donation>() ;
		fund f=FRepository.findById(id).get();
		DRepository.findAll().forEach(
				d->{
					if (d.getFund()==f)
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
