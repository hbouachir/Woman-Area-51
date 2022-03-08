package tn.esprit.spring.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
//import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import tn.esprit.spring.entities.User;
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
	
	public List<event>FindByTags(String tags){
		
		List<String> tagsList=new ArrayList<String>(Arrays.asList(tags.split("#")));
		List<event> list=new ArrayList<event>();
		tagsList.forEach(e->list.addAll(ERepository.retrieveByTag(e)));
		new LinkedHashSet<>(list);
		List<event> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(list));
		return listWithoutDuplicates;
		
	}
	
	public event EventOfTheMonth(LocalDateTime date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		String strDate = date.format(formatter); 
		String strDateInf =strDate.substring(0, 7)+"01 00:00:00";
		String strDateSup =strDate.substring(0, 7)+"30 23:59:59";
		Date DateInf = null;
		try {
			DateInf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(strDateInf);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Date DateSup = null;
		try {
			DateSup = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(strDateSup);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		ERepository.retrieveEventOfTheMonth(DateInf, DateSup);
		
		return ERepository.retrieveEventOfTheMonth(DateInf, DateSup).get(0);
	}
}
