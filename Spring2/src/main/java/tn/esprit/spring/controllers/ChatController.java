package tn.esprit.spring.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Complaint;
import tn.esprit.spring.entities.ExpertInterview;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.ExpertInterviewRepository;
import tn.esprit.spring.services.IChatService;
import tn.esprit.spring.services.IComplaintService;
import tn.esprit.spring.services.IExpertInterviewService;
import tn.esprit.spring.services.IUserService;
@RestController
public class ChatController {
	@Autowired
	IChatService ch ;
	@Autowired
	IComplaintService comp;
	@Autowired
	ComplaintController compcont;
	@Autowired
	ExpertInterviewController intcont;
	@Autowired
	IExpertInterviewService exint;
	@Autowired
	IUserService userservice;
	@Autowired
	ExpertInterviewRepository exrep;
	
	@GetMapping("/count/{iduser}")
	public int count(@PathVariable long iduser){	
		return exrep.countInterviews(iduser);
	}
	
	public ExpertInterview getBotInterview(long iduser){
		
		User us=userservice.ShowUser(iduser);
		//List<ExpertInterview> expint=new ArrayList<ExpertInterview>(us.getExpertinterviews());
		//List<ExpertInterview> expint=us.getExpertinterviews().toArray(new ArrayList<ExpertInterview>() {}) ;

		ExpertInterview inter=new ExpertInterview();
		/*for(int i=0;i<expint.size();){
			if(expint.get(i).getRating()==99){	
				
				inter=expint.get(i);
			return inter;
			}*/
		/*Iterator<ExpertInterview> it = us.getExpertinterviews().iterator() ;
		 while (it.hasNext()) {
			
			 ExpertInterview element = it.next() ;  // retourne un objet de type String
			 if(element.getRating()==99){	
					
				System.out.println(element) ;	
				return element;
		    
		}
		}*/
		Set<ExpertInterview> expint=us.getExpertinterviews();

		for (ExpertInterview element : expint) {
			if(element.getRating()==99){	
				
				//System.out.println(element) ;	
				return element;
		}
		}return inter;
	}
	public int countBotInterviews(long iduser){
		int n=0;
		for(int i=0;i<exint.showAllExpertInterview().size();i++){
			if(this.getBotInterview(iduser).getRating()==99)				
				n++;
		}
		return n;
	}
	
	
	@GetMapping("/sendMessage/{m}/{iduser}")
	public String sendMessage(@PathVariable String m,@PathVariable long iduser){
		//bot.chat("Hi");
		String rep=ch.sendMessage(m);

		if (m.contains("show all complaints")){
		List<Complaint> list = new ArrayList<Complaint>();
				list=comp.showAllComplaint();
		//System.out.println(list.toString()+"\nreponse:"+rep);
		System.out.println(list.toString());
		}
		else if (m.contains("delete complaint")){
			String id=m.substring(m.length() - 1); 
			comp.deleteComplaint(Long.parseLong(id));
		}
		else if (m.contains("add complaint")){
			Complaint c=new Complaint();
			c.setIdComplaint(55);
			
			compcont.addComplaint(c);
		}
		else if ((m.contains("schedule expert interview")&&(this.countBotInterviews(iduser)!=0)))
			return ch.sendMessage("unfinished interview");
		
		else if(m.contains("bot interview")){
			ExpertInterview inter=new ExpertInterview();
			//inter=this.getBotInterview(iduser);
			inter=exint.showExpertInterview(exrep.retrieveBotInterview(99).get(0));
			exint.showExpertInterview(inter.getIdExpertInterview());
			//return inter.toString();
		}
		
		else if(m.contains("show all interviews")){
			//List<ExpertInterview> list = new ArrayList<ExpertInterview>();
			exint.showAllExpertInterview();
		}
			
		
		else if ((m.contains("schedule expert interview")&&(this.countBotInterviews(iduser)==0))){
			ExpertInterview expint=new ExpertInterview();
			expint.setUser(userservice.ShowUser(iduser));
			expint.setRating(99);
			exint.addExpertInterview(expint);
			
		}else if (m.contains("interview date")/*&&(this.countBotInterviews(iduser)<=1)*/){
			ExpertInterview inter=new ExpertInterview();
			inter=exint.showExpertInterview(exrep.retrieveBotInterview(99).get(0));
			//Date firstDate = new Date(1994,8,28);			
			try{
				SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
				
				
				
			Date date = DateFor.parse(m.substring(15));
			String day =new SimpleDateFormat("EEEE").format(date);
			if (day.contains("samedi")||day.contains("dimanche")){
			
			return "cannot add interviews on "+day+"'s";
				}else{
					inter.setDateExpertInterview(date);
					exint.UpdateExpertInterview(inter);
				}
			//expint=null;
			//System.out.println("Date : "+date);
			}catch (ParseException e) {e.printStackTrace();}
			
			//expint.get(expint.size()-1).setExpertField("aaa");
			
		}
		else if(m.contains("expert field")/*&&(this.countBotInterviews(iduser)<=1)*/){
			ExpertInterview inter=new ExpertInterview();
			//inter=this.getBotInterview(iduser);
			inter=exint.showExpertInterview(exrep.retrieveBotInterview(99).get(0));
			inter.setExpertField(m.substring(13));
			exint.UpdateExpertInterview(inter);
			
			
		}
		else if(m.contains("interview type")/*&&(this.countBotInterviews(iduser)<=1)*/){
			ExpertInterview inter=new ExpertInterview();
			//inter=this.getBotInterview(iduser);
			inter=exint.showExpertInterview(exrep.retrieveBotInterview(99).get(0));
			if(m.substring(15).contains("online"))
			inter.setInterviewType(true);
			else if(m.substring(15).contains("local"))
				inter.setInterviewType(false);
			exint.UpdateExpertInterview(inter);
		}
		else if(m.contains("confirm interview")/*&&(this.countBotInterviews(iduser)<=1)*/){
			ExpertInterview inter=new ExpertInterview();
			//inter=this.getBotInterview(iduser);
			//List<Integer> botint=new ArrayList<Integer>();
			inter=exint.showExpertInterview(exrep.retrieveBotInterview(99).get(0));
			inter.setRating(0);
			List<Integer> intss=new ArrayList<Integer>();
			intss=exrep.retrieveRatings();
			for(int i=0;i<intss.size();i++){
				
				if (exrep.countInterviews(userservice.ShowUser(intss.get(i)).getUserId())<=2
						&&userservice.ShowUser(intss.get(i)).getRole().getExpertField().contains(inter.getExpertField())){
					inter.setExpert(userservice.ShowUser(intss.get(i)));
					inter.setUser(userservice.ShowUser(iduser));
					exint.UpdateExpertInterview(inter);
					break;
				}

			}
			
			
			/*
			List<User> users = new ArrayList<User>();
			List<User> experts= new ArrayList<User>();
			User expert = new User();
			users=userservice.ShowAllUser();
			//intcont.addExpertInterview(inter, iduser);
			experts=exint.searchExperts(users);
			expert=exint.ExpertToAffect(experts,inter.getExpertField());
			
					inter.setExpert(expert);
					
					exint.UpdateExpertInterview(inter);*/
					return "zzz";
		}
		
		
		
		
		//System.out.println("reponse : " + rep);
		//ch.sendMessage(m);
		//m.setM("wq");
		//ch.sendMessage(m);
		//bot.chat("wq");
		return rep;
}
	
	}
