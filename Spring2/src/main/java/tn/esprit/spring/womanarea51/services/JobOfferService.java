package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.JobOffer;

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