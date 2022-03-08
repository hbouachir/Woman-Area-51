package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.IntSummaryStatistics;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.event;
import tn.esprit.spring.entities.feedback;
import tn.esprit.spring.repositories.EventRepository;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.repositories.feedbackRepository;

@Service
public class feedbackServiceImp implements IfeedbackService {
	
	@Autowired
	feedbackRepository FeedRepository;
	
	@Autowired
	EventRepository ERepository; 
	
	@Autowired
	UserRepository URepository;
	
	
	//feedback instance created to indicate that a user is participating in an event, feedback attributes are initiated as null
	public void Participate(feedback e) {
		FeedRepository.save(e);
	}
	
	
	
	public feedback EditFeedback(feedback e) {
		feedback updated = new feedback();
		updated=FeedRepository.save(e);
		return updated;
		
	}
	
	
	
	public void DeleteFeedback(feedback e) {
		FeedRepository.delete(e);
	}
	
	
	
	public feedback FindFeedback(Long id) {
		feedback e=new feedback();
		e=FeedRepository.findById(id).get();
		return e;
	}
	
	
	public List<feedback> ListFeedbacks() {
		List<feedback> list=new ArrayList<feedback>() ;
		FeedRepository.findAll().forEach(e->list.add(e));
		return list;
	}
	
	public List<feedback> FindFeedbacksByEvent(Long id){
		
		List<feedback> list=new ArrayList<feedback>();
		event Event=ERepository.findById(id).get();
		FeedRepository.findAll().forEach(e->
		{
			if (e.getEvent_feedback()==Event)
				list.add(e);
		}
		);
		return list;
	}


	public List<feedback> FindFeedbacksByParticipant(Long id){
		
		List<feedback> list=new ArrayList<feedback>();
		User participant=URepository.findById(id).get();
		FeedRepository.findAll().forEach(e->
		{
			if (e.getParticipant()==participant)
				list.add(e);
		}
		);
		return list;
	}
	
	
	public List<feedback> FindFeedbackByUserAndEvent(Long userID, Long eventId) {
		
		List<feedback> list=new ArrayList<feedback>();
		User participant=URepository.findById(userID).get();
		event Event=ERepository.findById(eventId).get();
		FeedRepository.findAll().forEach(e->
		{
			if (e.getParticipant()==participant & e.getEvent_feedback()==Event)
				list.add(e);
		}
		);
		return list;
	}
	
	//calculate avg rating per event
	public double AVGEventRating(List<feedback> list) {
		
		double avg=0;
		
		DoubleSummaryStatistics AvgEventsRatingStatistics =list.stream().mapToDouble(feedback::getRating).summaryStatistics();
		avg=AvgEventsRatingStatistics.getAverage();
	
		return avg;
	}
	
	public double MinEventRating(List<feedback> list) {
		
		double min=0;
		
		DoubleSummaryStatistics AvgEventsRatingStatistics =list.stream().mapToDouble(feedback::getRating).summaryStatistics();
		min=AvgEventsRatingStatistics.getMin();
	
		return min;
	}
	
	public double MaxEventRating(List<feedback> list) {
		
		double max=0;
		
		DoubleSummaryStatistics AvgEventsRatingStatistics =list.stream().mapToDouble(feedback::getRating).summaryStatistics();
		max=AvgEventsRatingStatistics.getMax();
	
		return max;
	}
	
	public float calculRating(feedback f) {
		
		
		return ((f.getFutureEvents()+f.getLocation()+f.getObject()+f.getObject()+f.getOrganizers()+f.getRecommend()+f.getService())/7);
		
	}
}
