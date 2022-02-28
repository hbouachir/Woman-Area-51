package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.event;

public interface IEventService {
	
	void AddEvent(event e);
	
	event EditEvent(event e);
	
	void DeleteEvent(event e);
	
	event FindEvent(Long id);
	
	List<event> ListEvents();
	
	event AffectUserToEvent(Long EventId, Long userID);

}
