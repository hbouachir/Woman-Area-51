package tn.esprit.spring.womanarea51.services;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.eventStatus;
import tn.esprit.spring.womanarea51.entities.eventType;
import tn.esprit.spring.womanarea51.repositories.EventRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.repositories.feedbackRepository;

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
	
	public List<event> SearchByStatus(eventStatus s){
		List<event> list=new ArrayList<event>();
		ERepository.findAll().forEach(e->{
			if (e.getStatus()==s)
				list.add(e);
		});
		
		return list;
		
	}
	
	public List<event> SearchByType(eventType t){
		List<event> list=new ArrayList<event>();
		ERepository.findAll().forEach(e->{
			if (e.getType()==t)
				list.add(e);
		});
		
		return list;
		
	}
}
