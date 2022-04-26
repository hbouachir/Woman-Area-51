package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.JobOffer;
import tn.esprit.spring.repositories.JobOfferRepository;
import tn.esprit.spring.repositories.UserRepository;

@Service
public class JobOfferServicempl implements JobOfferService {
	@Autowired
	JobOfferRepository jr ;
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
	public JobOffer updateJobOffer(JobOffer j, Long idOffer) {
		// TODO Auto-generated method stub
		Optional<JobOffer> offer = jr.findById(idOffer);
		j.setIdOffer(idOffer);
		return jr.save(j);
	}

	@Override
	public void deleteOffer(Long idOffer) {
		// TODO Auto-generated method stub
		jr.deleteById(idOffer);
	}

	@Override
	public List<JobOffer> searchOfferByDomaine(String domaine) {
		// TODO Auto-generated method stub
		return jr.searchOfferByDomaine(domaine);
	}

	@Override
	public List<JobOffer> searchOfferByPlace(String offerPlace) {
		// TODO Auto-generated method stub
		return jr.searchOfferByPlace(offerPlace);
	}

	@Override
	public List<JobOffer> searchOfferMocCle(String mc) {
		// TODO Auto-generated method stub
		return jr.searchOfferMocCle(mc);
	}

}
