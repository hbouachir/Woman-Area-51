package tn.esprit.spring.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.event;
import tn.esprit.spring.entities.feedback;
import tn.esprit.spring.services.IEventService;
import tn.esprit.spring.services.IUserService;
import tn.esprit.spring.services.IfeedbackService;

@RestController
public class feedbackController {
	
	@Autowired
	IfeedbackService IFBS;
	
	@Autowired
	IUserService IUS;
	
	@Autowired
	IEventService IES;
	
	@Autowired
    private JavaMailSender emailSender;
	
	@PostMapping("/Participate/{id-user}/{id-event}")
	@ResponseBody
	void Partcipate(@PathVariable ("id-user") Long userId,@PathVariable ("id-event") Long eventId) {
		
		feedback f=new feedback();
		User user=IUS.ShowUser(userId);
		event e=IES.FindEvent(eventId);
		f.setParticipant(user);
		f.setEvent_feedback(e);
		IFBS.Participate(f);
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");  
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(user.getEmail()); 
        message.setSubject("Participation confirmation"); 
        message.setText("Hello "+ f.getParticipant().getFirstName() +" "+ f.getParticipant().getLastName()+","+"\n \n"
        		+"Your participation in the following event has been confirmed: \n"
        		+"Event :"+e.getDescription()
        		+"\nLocation: "+e.getEventLocation()
        		+"\nDate and time: "+dateFormat.format(e.getEventDate())
        		+".\n"
        		+ "Thank you for your participation. We look forward to hearing your feedback on the event after attending.\n\n"
        		+ "Regards,\n"
        		+ "The womenArea51 Team");
       
        emailSender.send(message);
		
		System.out.println(f.toString());
		
	}
	
	@PutMapping("/{id-user}/{id-event}/Feedback")
	feedback InputFeedback(@RequestBody feedback f,@PathVariable ("id-user") Long userId,@PathVariable ("id-event") Long eventId) {
		f=IFBS.calculRating(f);
		User user=IUS.ShowUser(userId);
		event e=IES.FindEvent(eventId);
		f.setParticipant(user);
		f.setEvent_feedback(e);
		System.out.println(f.getRating());
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");  
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(f.getParticipant().getEmail()); 
        message.setSubject("Participation confirmation"); 
        message.setText("Hello "+ f.getParticipant().getFirstName() +" "+ f.getParticipant().getLastName()+","+"\n \n"
        		+"Your feedback for the following event has been confirmed: \n"
        		+"Event :"+f.getEvent_feedback().getDescription()
        		+"\nLocation: "+f.getEvent_feedback().getEventLocation()
        		+"\nDate and time: "+dateFormat.format(f.getEvent_feedback().getEventDate())
        		+".\n"
        		+ "Thank you for your feedback. We look forward to your participation in future events.\n\n"
        		+ "Regards,\n"
        		+ "The womenArea51 Team");
        emailSender.send(message);
		
	return IFBS.EditFeedback(f);
				
	}
	
	
	@PutMapping("/remove-feedback/{feedbackId}")
	
	void RemoveFeedback(@PathVariable("feedbackId") Long id) {
		feedback f=IFBS.FindFeedback(id);
		f.setComment(null);
		f.setFutureEvents(null);
		f.setLocation(null);
		f.setObject(null);
		f.setOrganizers(null);
		f.setRating(null);
		f.setRecommend(null);
		f.setService(null);
		f.setAddedValue(null);
		IFBS.EditFeedback(f);
	}
	
	
	@DeleteMapping("/remove-participant/{feedbackId}")
	@ResponseBody
	void RemoveParticipant(@PathVariable("feedbackId") Long id) {
		feedback f=IFBS.FindFeedback(id);
		IFBS.DeleteFeedback(f);
	}
	
	
	@GetMapping("/find-feedbacks")
	List<feedback> FindFeedbacks() {
		return IFBS.ListFeedbacks();
	}
	
	@GetMapping("/find-feedbacks-byEvent/{event-id}")
	List<feedback> FindFeedbacksByEvent(@PathVariable("event-id") Long id) {
		return IFBS.FindFeedbacksByEvent(id);
	}
	
	@GetMapping("/find-feedbacks-byParticipant/{user-id}")
	List<feedback> FindFeedbacksByParticipant(@PathVariable("user-id") Long id) {
		return IFBS.FindFeedbacksByParticipant(id);
	}
	
	@GetMapping("/find-feedbacks/{event-id}/{user-id}")
	feedback FindFeedbacksByEventAndUSer(@PathVariable("event-id") Long eventId, @PathVariable("user-id") Long userId) {
		return IFBS.FindFeedbackByUserAndEvent(userId,eventId).get(0);
	}
	
	
	@GetMapping("/AVG-event-rating/{event-id}")
	double AvgRatingPerEvent(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		return IFBS.AVGEventRating(list);
	}
	
	@GetMapping("/Min-event-rating/{event-id}")
	double MinRatingPerEvent(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		return IFBS.MinEventRating(list);
	}
	
	@GetMapping("/Max-event-rating/{event-id}")
	double MaxRatingPerEvent(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		return IFBS.MaxEventRating(list);
	}
	

}
