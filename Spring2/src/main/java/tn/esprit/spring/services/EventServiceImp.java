package tn.esprit.spring.services;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.event;
import tn.esprit.spring.repositories.EventRepository;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.repositories.feedbackRepository;

@Service
public class EventServiceImp implements IEventService {
	@Autowired
	EventRepository ERepository;
	@Autowired
	UserRepository URepository;
	@Autowired
	feedbackRepository FRepository;
	
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
	
	public List<event>FindByTags(String tags){
		
		List<String> tagsList=new ArrayList<String>(Arrays.asList(tags.split("#")));
		List<event> list=new ArrayList<event>();
		tagsList.forEach(e->list.addAll(ERepository.retrieveByTag(e)));
		new LinkedHashSet<>(list);
		List<event> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(list));
		return listWithoutDuplicates;
		
	}
	
	
	
	public List<User> ParticipantsList (event e){
		
		List<User> participants= new ArrayList<User>();
		
		e.getFeedbacks().forEach(f->participants.add(f.getParticipant()));
	
		return participants;
		
	}
}
