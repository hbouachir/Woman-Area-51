package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.JobOffer;

public interface IJobOfferService {

    JobOffer addOffer (JobOffer j);
    List<JobOffer> showAllOffer();
    JobOffer showOneOffer (Long idOffer);
    JobOffer updateJobOffer(JobOffer j);
    void deleteOffer (Long idOffer);
    void AffectOffertoUser (Long idOffer,Long userId);
    
	
}
