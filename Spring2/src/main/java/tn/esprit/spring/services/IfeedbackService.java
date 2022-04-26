package tn.esprit.spring.services;

import java.text.ParseException;
import java.util.List;

import tn.esprit.spring.entities.event;
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
	
	public feedback calculRating(feedback f);
	
	public double MinEventRating(List<feedback> list);
	
	public double MaxEventRating(List<feedback> list);
	
	public List<event> FilterEventBycurrentDate() throws ParseException;
	
	public List<event> BestOfThisMonth(List<event> filtered);
	
	public double MaxRating() throws ParseException;
}
