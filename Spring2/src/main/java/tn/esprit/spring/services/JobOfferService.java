package tn.esprit.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.JobOffer;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.JobOfferRepository;
import tn.esprit.spring.repositories.UserRepository;

@Service
public class JobOfferService implements IJobOfferService {

	@Autowired
	JobOfferRepository jr;
	@Autowired
	UserRepository ur ;
	@Override
	public JobOffer addOffer(JobOffer j) {
		// TODO Auto-generated method stub
		return jr.save(j);
	}
	@Override
	public List<JobOffer> showAllOffer() {
		// TODO Auto-generated method stub
		return (List<JobOffer>) jr.findAll() ;
	}
	@Override
	public JobOffer showOneOffer(Long idOffer) {
		// TODO Auto-generated method stub
		return  jr.findById(idOffer).orElse(null);
	}
	@Override
	public JobOffer updateJobOffer(JobOffer j) {
		// TODO Auto-generated method stub
		return jr.save(j);
	}
	@Override
	public void deleteOffer(Long idOffer) {
		// TODO Auto-generated method stub
     jr.deleteById(idOffer);
	}
@Transactional
	@Override
	public void AffectOffertoUser(Long idOffer, Long userId) {
		// TODO Auto-generated method stub
		JobOffer j = jr.findById(idOffer).get();
			User u = ur.findById(userId).get();
			u.getJobOffers().add(j);
			
	}

}
