package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.event;
import tn.esprit.spring.repositories.EventRepository;
import tn.esprit.spring.repositories.UserRepository;

@Service
public class EventServiceImp implements IEventService {
	@Autowired
	EventRepository ERepository;
	@Autowired
	UserRepository URepository;
	
	public void AddEvent(event e) {
		ERepository.save(e);
	}
	
	
	
	public event EditEvent(event e) {
		event updated = new event();
		updated=ERepository.save(e);
		return updated;
		
	}
	
	
	
	public void DeleteEvent(event e) {
		ERepository.delete(e);
	}
	
	
	
	public event FindEvent(Long id) {
		event e=new event();
		e=ERepository.findById(id).get();
		return e;
	}
	
	
	public List<event> ListEvents() {
		List<event> list=new ArrayList<event>() ;
		ERepository.findAll().forEach(e->list.add(e));
		return list;
	}
	
	public event AffectUserToEvent(Long EventId, Long userID) {
		event e=ERepository.findById(EventId).get();
		User u=URepository.findById(userID).get();
		Set<User> users=e.getParticipants();
		users.add(u);
		e.setParticipants(users);
		return (e);
	}


}
