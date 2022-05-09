package tn.esprit.spring.womanarea51.services;


import java.util.List;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.eventStatus;
import tn.esprit.spring.womanarea51.entities.eventType;

public interface IEventService {
	
	void AddEvent(event e);
	
	event EditEvent(event e);
	
	void DeleteEvent(event e);
	
	event FindEvent(Long id);
	
	List<event> ListEvents();
	
	public List<event>FindByTags(String tags);
	
	public List<User> ParticipantsList (event e);
	
	public List<event> SearchByStatus(eventStatus s);
	
	public List<event> SearchByType(eventType t);
	
	public List<User>GetStaff(Long id);
	
	public List<User>GetSpeakers(Long id);
	
	public void AddSpeaker(long id, long idu);
	
	public void AddStaff(long id, long idu);
	
	public User removeSpeaker(long id, long idu);
	
	public User removeStaff(long id, long idu);
	
	
	

}
