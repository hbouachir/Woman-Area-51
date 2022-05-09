package tn.esprit.spring.womanarea51.services;

import java.text.ParseException;
import java.util.List;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.feedback;

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
	
	public List<event>Upcomingevents(User u);
	
	public double AVGEventObjectRating(List<feedback> list);
	
	public double MinEventObjectRating(List<feedback> list) ;
	
	public double MaxEventObjectRating(List<feedback> list);
	
	public double AVGEventOrganizersRating(List<feedback> list) ;
	
	public double MinEventOrganizersRating(List<feedback> list);
	
	public double MaxEventOrganizersRating(List<feedback> list);
	
	public double AVGEventLocationRating(List<feedback> list);
	
	public double MinEventLocationRating(List<feedback> list);
	
	public double MaxEventLocationRating(List<feedback> list);
	
	public double AVGEventAddedValueRating(List<feedback> list);
	
	public double MinEventAddedValueRating(List<feedback> list);
	
	public double MaxEventAddedValueRating(List<feedback> list);

	public double AVGEventRecommendRating(List<feedback> list);
	
	public double MinEventRecommendRating(List<feedback> list) ;
	
	public double MaxEventRecommendRating(List<feedback> list);
	
	public double AVGEventServiceRating(List<feedback> list);
	
	public double MinEventServiceRating(List<feedback> list);
	
	public double MaxEventServiceRating(List<feedback> list);
	
	public double AVGEventFutureEventsRating(List<feedback> list);
	
	public double MinEventFutureEventsRating(List<feedback> list);
	
	public double MaxEventFutureEventsRating(List<feedback> list);
	
}
