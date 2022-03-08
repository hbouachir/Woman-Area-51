package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.feedback;

public interface IfeedbackService {
	
	public void Participate(feedback e);
	
	public feedback EditFeedback(feedback e);
	
	public void DeleteFeedback(feedback e);
	
	public feedback FindFeedback(Long id);
	
	public List<feedback> ListFeedbacks();
	
	public List<feedback> FindFeedbacksByEvent(Long id);
	
	public List<feedback> FindFeedbacksByParticipant(Long id);
	
	public List<feedback> FindFeedbackByUserAndEvent(Long userID, Long eventId);
	
	public double AVGEventRating(List<feedback> list);
	
	public float calculRating(feedback f);
	
	public double MinEventRating(List<feedback> list);
	
	public double MaxEventRating(List<feedback> list);
}
