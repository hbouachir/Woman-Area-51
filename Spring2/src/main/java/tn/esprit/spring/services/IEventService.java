package tn.esprit.spring.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.event;

public interface IEventService {
	
	void AddEvent(event e);
	
	event EditEvent(event e);
	
	void DeleteEvent(event e);
	
	event FindEvent(Long id);
	
	List<event> ListEvents();
	
	public List<event>FindByTags(String tags);
	
	public event EventOfTheMonth(LocalDateTime now);

}
