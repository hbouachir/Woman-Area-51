package tn.esprit.spring.services;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.JobOffer;

public interface JobOfferService {

	 	JobOffer addOffer (JobOffer j);
	    List<JobOffer> showAllOffer();
	    JobOffer showOneOffer (Long idOffer);
	    JobOffer updateJobOffer(JobOffer j,Long idOffer);
	    void deleteOffer (Long idOffer);
	    List<JobOffer> searchOfferByDomaine( String domaine);
	    List<JobOffer> searchOfferByPlace(String offerPlace);
	    List<JobOffer> searchOfferMocCle(String mc);
}
