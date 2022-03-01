package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Complaint;

public interface IComplaintService {

	Complaint addComplaint(Complaint c);
	Complaint showComplaint(Long idComplaint);
	 List<Complaint> showAllComplaint();
	 Complaint UpdateComplaint (Complaint c);
	 void deleteComplaint (Long idComplaint);
	
}
