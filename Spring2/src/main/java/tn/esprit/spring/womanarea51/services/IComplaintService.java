package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.Complaint;

public interface IComplaintService {

	Complaint addComplaint(Complaint c);
	Complaint showComplaint(Long idComplaint);
	 List<Complaint> showAllComplaint();
	 Complaint UpdateComplaint (Complaint c);
	 void deleteComplaint (Long idComplaint);
	
}
