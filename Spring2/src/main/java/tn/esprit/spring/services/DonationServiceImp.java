package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.donation;
import tn.esprit.spring.repositories.DonationRepository;

@Service
public class DonationServiceImp implements IDonationService {
	
	@Autowired
	DonationRepository DRepository;
	
	@Autowired
    private JavaMailSender emailSender;
	
	 
	public void AddDonation(donation d) {
		DRepository.save(d);
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(d.getUser().getEmail()); 
        message.setSubject("Donation confirmation"); 
        message.setText("Hello "+d.getUser().getFirstName()+" "+d.getUser().getLastName()+","+"\n \n"
        		+"Your donation amount of "+String.valueOf(d.getAmount())
        		+"DT has been confirmed for "+d.getFund().getFundDescription()
        		+".\n"
        		+ "Thank you for your contribution.\n\n"
        		+ "Regards,\n"
        		+ "The womenArea51 Team");//add Fund link after !!!:D
        emailSender.send(message);
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
