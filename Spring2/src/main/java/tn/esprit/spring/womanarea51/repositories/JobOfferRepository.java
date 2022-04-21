package tn.esprit.spring.womanarea51.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.JobOffer;

@Repository
public interface JobOfferRepository extends CrudRepository<JobOffer, Long> {
    @Query("Select j FROM JobOffer j where j.domaine=:domaine")
    List<JobOffer> searchOfferByDomaine(@Param("domaine") String domaine);

    @Query("Select j FROM JobOffer j where j.offerPlace=:offerPlace")
    List<JobOffer> searchOfferByPlace(@Param("offerPlace") String offerPlace);

    @Query("Select j FROM JobOffer j where j.OfferTitle like :x or j.domaine like :x  or j.companyName like :x or j.offerPlace like :x or j.salaire like :x  or j.typeContract like :x ")
    List<JobOffer> searchOfferMocCle(@Param("x")String mc);





}