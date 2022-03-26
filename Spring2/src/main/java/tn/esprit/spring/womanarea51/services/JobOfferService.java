package tn.esprit.spring.womanarea51.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.JobOffer;
import tn.esprit.spring.womanarea51.repositories.JobOfferRepository;

@Service
public class JobOfferService implements IJobOfferService {

	@Autowired
	JobOfferRepository jr;
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

}
