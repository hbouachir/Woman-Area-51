package tn.esprit.spring.womanarea51.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
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

import com.google.zxing.WriterException;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.eventStatus;
import tn.esprit.spring.womanarea51.entities.eventType;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IEmailingService;
import tn.esprit.spring.womanarea51.services.IEventService;
import tn.esprit.spring.womanarea51.services.IUserService;
import tn.esprit.spring.womanarea51.services.IfeedbackService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class eventController {

	@Autowired
	IEventService IES;

	@Autowired
	IfeedbackService IFS;

	@Autowired
	UserRepository UR;

	@Autowired
	IUserService US;

	@Autowired
	IEmailingService IemailS;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/Add-event")
	void AddEvent(@RequestBody event e, Authentication authentication) {
		User U = UR.findByUsername(authentication.getName()).orElse(null);
		e.setAdmin(U);
		IES.AddEvent(e);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/event/Update")
	event EditEvent(@RequestBody event e, Authentication authentication){
		event event =IES.FindEvent(e.getEventId());
		List<User>Staff=event.getStaff();
		List<User>Speakers=event.getSpeakers();
		List<User>participants=IES.ParticipantsList(event);
		Staff.forEach(u->{
			String badge;
			try {
				try {
					badge = IemailS.GeneratgeBadgeByType(u, e, "STAFF");
					IemailS.eventUpdate(u, e, badge);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
				IemailS.DeleteBadgeFiles(u, e);
			
		});
		Speakers.forEach(u->{
			String badge;
			try {
				try {
					badge = IemailS.GeneratgeBadgeByType(u, e, "SPEAKER");
					IemailS.eventUpdate(u, e, badge);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
				IemailS.DeleteBadgeFiles(u, e);
			
		});
		participants.forEach(u->{
			String badge;
			try {
				try {
					badge = IemailS.GenerateBadge(u, e);
					IemailS.eventUpdate(u, e, badge);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
				IemailS.DeleteBadgeFiles(u, e);
			
		});
		return IES.EditEvent(e);
				
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/remove-event/{eventId}")

	void RemoveEvent(@PathVariable("eventId") Long eventId, Authentication authentication) {
		event e = IES.FindEvent(eventId);
		IES.DeleteEvent(e);
	}

	@GetMapping("/find-event/{event-id}")
	event FindEventById(@PathVariable("event-id") Long eventId) {
		return IES.FindEvent(eventId);
	}

	@GetMapping("/find-all-events")
	List<event> FindAllEvents() {
		return IES.ListEvents();
	}

	@GetMapping("/find-events-by-tags/{tags}")
	List<event> FindEventsByTags(@PathVariable("tags") String tags) {

		return IES.FindByTags(tags);

	}

	@GetMapping("/{event-id}/partipants")
	List<User> EventParticipants(@PathVariable("event-id") Long eventId, Authentication authentication) {
		event e = IES.FindEvent(eventId);
		return IES.ParticipantsList(e);
	}

	@GetMapping("/event-of-the-month")
	List<event> EventOfCurrentMonth() throws ParseException {

		return IFS.BestOfThisMonth(IFS.FilterEventBycurrentDate());
	}

	@GetMapping("/event-by-status/{s}")
	public List<event> SearchByStatus(@PathVariable("s") eventStatus s) {
		return IES.SearchByStatus(s);

	}

	@GetMapping("/event-by-type/{t}")
	public List<event> SearchByType(@PathVariable("t") eventType t) {
		return IES.SearchByType(t);

	}

	@GetMapping("/generateParticipantBadge/{id}/{uid}")
	public void generateParticipantBadge(@PathVariable("id") Long id,@PathVariable("uid") Long uid, Authentication authentication) throws Exception {
		User U = UR.getById(uid);
		event e = IES.FindEvent(id);	
		IemailS.ParticipationConfirmation(U, e, IemailS.GenerateBadge(U, e));
		IemailS.DeleteBadgeFiles(U, e);

	}

	@GetMapping("/generateBadge/{id}/{uid}/{type}")
	public void generateBadge(@PathVariable("id") Long id, @PathVariable("uid") Long uid,
			@PathVariable("type") String type) throws Exception {
		User U = UR.getById(uid);
		event e = IES.FindEvent(id);
		String badge=IemailS.GeneratgeBadgeByType(U, e, type);
		if (type.contentEquals("STAFF")) {
			
			IemailS.StaffMail(U, e, badge);
		}
		else if(type.contentEquals("SPEAKER")) {
			
			IemailS.SpeakerMail(U, e, badge); }
		
		IemailS.DeleteBadgeFiles(U, e);

	}

	@GetMapping("/eventSpeakers/{id}")
	public List<User> GetSpeakers(@PathVariable("id") Long id) {

		return IES.GetSpeakers(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/eventStaff/{id}")
	public List<User> GetStaff(@PathVariable("id") Long id, Authentication authentication) {

		return IES.GetStaff(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/AddSpeaker/{id}/{idu}")
	public void addSpeaker(@PathVariable("id") Long id, @PathVariable("idu") Long idu, Authentication authentication)
			throws Exception, Exception, Exception {
		User u = US.findOne(idu);
		event e = IES.FindEvent(id);
		if (!IES.GetSpeakers(id).contains(u)) {
			IES.AddSpeaker(id, idu);
			String Badge = IemailS.GeneratgeBadgeByType(u, e, "SPEAKER");
			IemailS.SpeakerMail(u, e, Badge);
			IemailS.DeleteBadgeFiles(u, e);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/AddStaff/{id}/{idu}")
	public void addStaff(@PathVariable("id") Long id, @PathVariable("idu") Long idu, Authentication authentication)
			throws Exception, Exception, Exception {

		User u = US.findOne(idu);
		event e = IES.FindEvent(id);
		if (!IES.GetStaff(id).contains(u)) {
			IES.AddStaff(id, idu);
			String Badge = IemailS.GeneratgeBadgeByType(u, e, "STAFF");
			IemailS.StaffMail(u, e, Badge);
			IemailS.DeleteBadgeFiles(u, e);

		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/removeSpeaker/{id}/{idu}")
	public void RemoveSpeaker(@PathVariable("id") Long id, @PathVariable("idu") Long idu,
			Authentication authentication) {
		User u = IES.removeSpeaker(id, idu);
		System.out.println(u.getId());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/removeStaff/{id}/{idu}")
	public void RemoveStaff(@PathVariable("id") Long id, @PathVariable("idu") Long idu, Authentication authentication) {
		User u = IES.removeStaff(id, idu);
		System.out.println(u.getId());
	}
	
	@GetMapping("/getEventTags")
	public List<String> GetTags(){
		return IES.getTags();
	}
	
	@GetMapping("/GetEventAdmin/{id}")
	public User GetAdmin(@PathVariable("id")Long id) {
		System.out.println(IES.FindEvent(id).getAdmin()+"**************************");
		return IES.FindEvent(id).getAdmin();
	}

}
