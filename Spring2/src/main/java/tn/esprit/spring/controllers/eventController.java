package tn.esprit.spring.controllers;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import tn.esprit.spring.services.IEventService;
import tn.esprit.spring.services.IfeedbackService;

@RestController
public class eventController {

	@Autowired
	IEventService IES;
	
	@Autowired
	IfeedbackService IFS;
	
	
	@PostMapping("/Add-event")
	void AddEvent(@RequestBody event e){

		IES.AddEvent(e);
		
	}
	
	@PutMapping("/event/Update")
	event EditEvent(@RequestBody event e) {
		
		return IES.EditEvent(e);
				
	}
	
	
	@DeleteMapping("/remove-event/{eventId}")
	@ResponseBody
	void RemoveEvent(@PathVariable("eventId") Long eventId) {
		event e=IES.FindEvent(eventId);
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
	List<User> EventParticipants(@PathVariable("event-id")Long eventId){
		event e=IES.FindEvent(eventId);
		return IES.ParticipantsList(e);
	}
	
	@GetMapping("/event-of-the-month")
	List<event> EventOfCurrentMonth() throws ParseException {
		
		
		return IFS.BestOfThisMonth(IFS.FilterEventBycurrentDate());
	}
	
	
	
}
