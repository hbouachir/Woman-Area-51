package tn.esprit.spring.services;


import java.util.List;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.event;

public interface IEventService {
	
	void AddEvent(event e);
	
	event EditEvent(event e);
	
	void DeleteEvent(event e);
	
	event FindEvent(Long id);
	
	List<event> ListEvents();
	
	public List<event>FindByTags(String tags);
	
	public List<User> ParticipantsList (event e);

}
