package tn.esprit.spring.womanarea51.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.controllers.GoogleCalController;
import tn.esprit.spring.womanarea51.entities.Complaint;
import tn.esprit.spring.womanarea51.entities.EExpertField;
import tn.esprit.spring.womanarea51.entities.ExpertInterview;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.ExpertInterviewRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;
import tn.esprit.spring.womanarea51.services.IChatService;
import tn.esprit.spring.womanarea51.services.IComplaintService;
import tn.esprit.spring.womanarea51.services.IExpertInterviewService;
import tn.esprit.spring.womanarea51.services.IRoleService;
import tn.esprit.spring.womanarea51.services.IUserService;
@RestController
public class ChatBotController {
	@Autowired
	IChatService ch ;
	@Autowired
	IComplaintService comp;
	
	
	@Autowired
	IExpertInterviewService exint;
	@Autowired
	IUserService userservice;
	@Autowired
	ExpertInterviewRepository exrep;
	@Autowired
	IRoleService rservice;
	@Autowired
	UserRepository urep;
	@Autowired
	GoogleCalController calcont;
	
	@GetMapping("/count/{iduser}")
	public int count(@PathVariable long iduser){	
		return exrep.countInterviews(iduser);
	}
	
	public ExpertInterview getBotInterview(long iduser){
		
		User us=userservice.findOne(iduser);
		//List<ExpertInterview> expint=new ArrayList<ExpertInterview>(us.getExpertinterviews());
		//List<ExpertInterview> expint=us.getExpertinterviews().toArray(new ArrayList<ExpertInterview>() {}) ;

		ExpertInterview inter=new ExpertInterview();
		
		Set<ExpertInterview> expint=us.getExpertinterviews();

		for (ExpertInterview element : expint) {
			if(element.getRating()==99){	
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
	
	
	@GetMapping("/sendMessage/{m}")
	public String sendMessage(@PathVariable String m, Authentication authentication) throws IOException{
		//bot.chat("Hi");
		String rep=ch.sendMessage(m);
		//ch.sendMessage(m);
		User U = new User();
		try {
			UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
			U = urep.findByUsername(U1.getUsername())
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		}catch (Exception e){
			System.out.println(e);
		}
		

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
			
			comp.addComplaint(c);
		}
		else if ((m.contains("schedule expert interview")&&(exrep.countBotInterviews(99)!=0)))
			return ch.sendMessage("unfinished interview");
		
		else if(m.contains("bot interview")){
			ExpertInterview inter=new ExpertInterview();
			//inter=this.getBotInterview(iduser);
			inter=exint.showExpertInterview(exrep.retrieveBotInterview(99).get(0));
			inter=exint.showExpertInterview(inter.getIdExpertInterview());
			return rep+"\nExpert: "+inter.getExpert()+"\nExperts field: "+inter.getExpertField()+"\nInterview date: "+inter.getDateExpertInterview()+"\nRating: "+inter.getRating();
			//return inter.toString();
		}
		
		else if(m.contains("show all interviews")){
			//List<ExpertInterview> list = new ArrayList<ExpertInterview>();
			exint.showAllExpertInterview();
		}
			
		
		else if ((m.contains("schedule expert interview")&&(this.countBotInterviews(U.getId())==0))){
			ExpertInterview expint=new ExpertInterview();
			expint.setUser(userservice.findOne(U.getId()));
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
			if(m.contains("Harrassment"))
			inter.setExpertField(EExpertField.Harrassment);
			else if(m.contains("Divroce"))
				inter.setExpertField(EExpertField.Divorce);
			else 
				inter.setExpertField(EExpertField.Social);
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
			String rolls=null;
			intss=exrep.retrieveRatings();
			for(int i=0;i<=intss.size()-1;i++){
				rolls=null;
				
				
				rolls=exrep.getRoleField(exrep.getRoles(userservice.findOne(intss.get(i)).getId()));
				//roles=roles.concat(rolls);
				if (exrep.countInterviews(userservice.findOne(intss.get(i)).getId())<=5
						&&rolls.contains(inter.getExpertField().toString())){
					
							inter.setExpert(userservice.findOne(intss.get(i)));
							inter.setUser(userservice.findOne(U.getId()));
							if(inter.getInterviewType()==true){
								SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
								//String date=DateFor.format(inter.getDateExpertInterview());
								calcont.addeventtest(DateFor.format(inter.getDateExpertInterview()),U.getEmail());
								//return date;
								}
							exint.UpdateExpertInterview(inter);
							return rolls;
							
							
					
					
						
					
					
				}
				//exint.UpdateExpertInterview(inter);
				//return rolls.toString()+" with id: "+roles;
				//return rolls+" with id: "+exrep.getRoles(userservice.findOne(intss.get(i)).getId());
				//return intss.toString()+rolls+" with id: "+exrep.getRoles(userservice.findOne(intss.get(i)).getId());

			}
			
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
