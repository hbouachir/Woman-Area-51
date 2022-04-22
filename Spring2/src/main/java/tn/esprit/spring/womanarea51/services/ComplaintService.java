package tn.esprit.spring.womanarea51.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.Complaint;
import tn.esprit.spring.womanarea51.repositories.ComplaintRepository;
@Service
public class ComplaintService implements IComplaintService {

	@Autowired
	ComplaintRepository comp ;
	@Override
	public Complaint addComplaint(Complaint c) {
		// TODO Auto-generated method stub
		return comp.save(c);
	}
	@Override
	public List<Complaint> showAllComplaint() {
		// TODO Auto-generated method stub
		return ( List<Complaint>) comp.findAll() ;
	}
	@Override
	public Complaint UpdateComplaint(Complaint c) {
		// TODO Auto-generated method stub
		return comp.save(c);
	}
	@Override
	public void deleteComplaint(Long idComplaint) {
		// TODO Auto-generated method stub
		comp.deleteById(idComplaint);
	}
	@Override
	public Complaint showComplaint(Long idComplaint) {
		// TODO Auto-generated method stub
		return comp.findById(idComplaint).orElse(null);
	}
	
}
