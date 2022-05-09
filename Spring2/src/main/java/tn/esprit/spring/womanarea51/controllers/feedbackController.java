package tn.esprit.spring.womanarea51.controllers;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.feedback;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IEmailScheduling;
import tn.esprit.spring.womanarea51.services.IEmailingService;
import tn.esprit.spring.womanarea51.services.IEventService;
import tn.esprit.spring.womanarea51.services.IUserService;
import tn.esprit.spring.womanarea51.services.IfeedbackService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class feedbackController {
	
	@Autowired
    IfeedbackService IFBS;
	
	@Autowired
	IUserService IUS;
	
	@Autowired
    IEventService IES;
	
	@Autowired
	UserRepository UR;
	
	@Autowired
	IEmailScheduling IemailS;
	
	@Autowired
	IEmailingService IEmailingS;
	
	
	
	
	
	@GetMapping("/participate/{idevent}")
	
	void Partcipate( Authentication authentication, @PathVariable ("idevent") Long eventId) throws Exception{
		User U=UR.findByUsername(authentication.getName()).orElse(null);
		System.out.println(U.getId()+"********************");
		feedback f=new feedback();
		
		event e=IES.FindEvent(eventId);
		
		f.setParticipant(U);
		
		
		if (e.getPlaces()!= null)
		{
			e.setPlaces(e.getPlaces()-1);
			System.out.println(e.getPlaces());
			IES.EditEvent(e);
		}
		f.setEvent_feedback(e);
		IFBS.Participate(f);
		if (e.getType().toString()=="INPERSON"){
			String Badge = IEmailingS.GenerateBadge(U, e);
			IEmailingS.ParticipationConfirmation(U, e, Badge);
			IEmailingS.DeleteBadgeFiles(U, e);
		} 
		else
			IEmailingS.VirtualEvent(U, e);
		
		
		IemailS.scheduleEmail(U.getEmail(), U.getUsername(), e);
		
		
	}
	

	@PutMapping("/Feedback/{event}")
	feedback InputFeedback(@RequestBody feedback f,@PathVariable("event") Long eventId, Authentication authentication) throws Exception  {
		User U=UR.findByUsername(authentication.getName()).orElse(null);
		f=IFBS.calculRating(f);
		Date date=new Date();
		System.out.println(date);
		System.out.println(f.getRating().toString());
		event e=IES.FindEvent(eventId);
		f.setParticipant(U);
		f.setEvent_feedback(e);
		f.setDate(date);
		System.out.println(e.getEventId());

		IEmailingS.feedbackConfirmation(f, U);
		
	return IFBS.EditFeedback(f);
				
	}
	
	
	@GetMapping("/remove-feedback/{feedbackId}")
	
	void RemoveFeedback(Authentication authentication,@PathVariable("feedbackId") Long id) {
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
	
	@DeleteMapping("/Cancel-participation/{feedbackId}")
	void RemoveParticipant(Authentication authentication,@PathVariable("feedbackId") Long id) throws Exception {
		feedback f=IFBS.FindFeedback(id);
		event e=f.getEvent_feedback();
		e.setPlaces(e.getPlaces()+1);
		IES.EditEvent(e);
		IEmailingS.CancelParticipation(f);
		IFBS.DeleteFeedback(f);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/find-feedbacks")
	List<feedback> FindFeedbacks() {
		return IFBS.ListFeedbacks();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
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
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.AVGEventRating(fb);
	}
	
	@GetMapping("/Min-event-rating/{event-id}")
	double MinRatingPerEvent(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MinEventRating(fb);
	}
	
	@GetMapping("/Max-event-rating/{event-id}")
	double MaxRatingPerEvent(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MaxEventRating(fb);
	}
	
	@GetMapping("My-upcoming-events")
	public List<event>Upcomingevents(Authentication authentication){
		User U=UR.findByUsername(authentication.getName()).orElse(null);
		return IFBS.Upcomingevents(U);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("Upcoming-events-per-user/{user}")
	public List<event>UpcomingeventsPerUer(Authentication authentication,@PathVariable("user")Long id){
		User U=IUS.findOne(id);
		return IFBS.Upcomingevents(U);
	}
	
	@GetMapping("/AVGEventObjectRating/{event-id}")
	double AVGEventObjectRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.AVGEventObjectRating(fb);
	}
	
	@GetMapping("/MinEventObjectRating/{event-id}")
	double MinEventObjectRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MinEventObjectRating(fb);
	}
	
	@GetMapping("/MaxEventObjectRating/{event-id}")
	double MaxEventObjectRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MaxEventObjectRating(fb);
	}

	@GetMapping("/AVGEventOrganizersRating/{event-id}")
	double AVGEventOrganizersRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.AVGEventOrganizersRating(fb);
	}
	
	@GetMapping("/MinEventOrganizersRating/{event-id}")
	double MinEventOrganizersRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MinEventOrganizersRating(fb);
	}
	
	@GetMapping("/MaxEventOrganizersRating/{event-id}")
	double MaxEventOrganizersRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MaxEventOrganizersRating(fb);
	}
	

	@GetMapping("/AVGEventLocationRating/{event-id}")
	double AVGEventLocationRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.AVGEventLocationRating(fb);
	}
	
	@GetMapping("/MinEventLocationRating/{event-id}")
	double MinEventLocationRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MinEventLocationRating(fb);
	}
	
	@GetMapping("/MaxEventLocationRating/{event-id}")
	double MaxEventLocationRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MaxEventLocationRating(fb);
	}
	
	@GetMapping("/AVGEventAddedValueRating/{event-id}")
	double AVGEventAddedValueRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.AVGEventAddedValueRating(fb);
	}
	
	@GetMapping("/MinEventAddedValueRating/{event-id}")
	double MinEventAddedValueRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MinEventAddedValueRating(fb);
	}
	
	@GetMapping("/MaxEventAddedValueRating/{event-id}")
	double MaxEventAddedValueRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MaxEventAddedValueRating(fb);
	}
	
	@GetMapping("/AVGEventRecommendRating/{event-id}")
	double AVGEventRecommendRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.AVGEventRecommendRating(fb);
	}
	
	@GetMapping("/MinEventRecommendRating/{event-id}")
	double MinEventRecommendRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MinEventRecommendRating(fb);
	}
	
	@GetMapping("/MaxEventRecommendRating/{event-id}")
	double MaxEventRecommendRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MaxEventRecommendRating(fb);
	}
	
	@GetMapping("/AVGEventServiceRating/{event-id}")
	double AVGEventServiceRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.AVGEventServiceRating(fb);
	}
	
	@GetMapping("/MinEventServiceRating/{event-id}")
	double MinEventServiceRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MinEventServiceRating(fb);
	}
	
	@GetMapping("/MaxEventServiceRating/{event-id}")
	double MaxEventServiceRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MaxEventRecommendRating(fb);
	}
	
	@GetMapping("/AVGEventFutureEventsRating/{event-id}")
	double AVGEventFutureEventsRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.AVGEventFutureEventsRating(fb);
	}
	
	@GetMapping("/MinEventFutureEventsRating/{event-id}")
	double MinEventFutureEventsRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MinEventFutureEventsRating(fb);
	}
	
	@GetMapping("/MaxEventFutureEventsRating/{event-id}")
	double MaxEventFutureEventsRating(@PathVariable("event-id") Long eventId) {
		List<feedback> list=IFBS.FindFeedbacksByEvent(eventId);
		List<feedback> fb= new ArrayList<feedback>();
		list.forEach(f->{
			if (f.getRating()!=null)
				fb.add(f);
		});
		return IFBS.MaxEventRecommendRating(fb);
	}
	
	@PostMapping("/sendPersonalizedMail/{id-user}/{subject}")
	String SendMailPers(@PathVariable("id-user") Long uId,@PathVariable("subject")String subject,@RequestBody String body) throws Exception {
		User U=IUS.findOne(uId);
		IEmailingS.Personalized(U, subject, body);
		return "Mail Sent";
	}
	@GetMapping("/GetFeedBackUser/{id}")
	User getFeedBackUser(@PathVariable("id")Long id) {
		return IFBS.FindFeedback(id).getParticipant();
	}

	@GetMapping("/GetParticipatedEvents/{id}")
	List<event>GetParticipatedEvents(@PathVariable("id")Long id){
		List<event> events= new ArrayList<event>();
		IFBS.FindFeedbacksByParticipant(id).forEach(f->{
			events.add(f.getEvent_feedback());
		});
		return events;
	}
}
