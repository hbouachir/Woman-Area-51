package tn.esprit.spring.womanarea51.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.womanarea51.entities.PublicComplaint;
import tn.esprit.spring.womanarea51.repositories.PublicComplaintRepository;

@Service
public class PublicComplaintServiceImpl implements PublicComplaintService {

    @Autowired
    PublicComplaintRepository pcr;


    @Override
    public void addPublicComplaint(PublicComplaint publicComplaint){
        pcr.save(publicComplaint);
    }
}
