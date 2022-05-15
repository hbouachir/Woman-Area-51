package tn.esprit.spring.womanarea51.controllers;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.Attachment;
import tn.esprit.spring.womanarea51.entities.Complaint;
import tn.esprit.spring.womanarea51.entities.ComplaintType;
import tn.esprit.spring.womanarea51.entities.FileComplaint;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.ComplaintRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;
import tn.esprit.spring.womanarea51.services.AttachmentService;
import tn.esprit.spring.womanarea51.services.FileComplaintService;
import tn.esprit.spring.womanarea51.services.IComplaintService;
@RestController
public class ComplaintController {
	@Autowired
	IComplaintService comp ;
	@Autowired
	UserRepository urep;
	@Autowired
	ComplaintRepository crep;
	@Autowired
    FileComplaintService fs;
	@Autowired
    AttachmentService as;
	
	@PostMapping(path="/Complaint/{idComplaint}/addFile")
    
    public void addFile(@PathVariable long idComplaint, @RequestParam("file") MultipartFile file, Authentication authentication) {
        User U = urep.findByUsername(authentication.getName()).orElse(null);
        
        Complaint c= comp.showComplaint(idComplaint);     
        FileComplaint fichi=new FileComplaint();
        fichi.setFileName(file.getOriginalFilename());
        Attachment attat=new Attachment();
        attat.setComplaint(c);
        fichi.setAttachment(attat);
        
        if (c.getAttachments()==null)
        {
        as.addAttachment(attat);
        c.getAttachments().add(attat);
        }else
        	c.getAttachments().add(attat);
        	
        
		fs.addFileComplaint(file, idComplaint,U,fichi);
		comp.UpdateComplaint(c);
				
		
		
   
    }
	
	@PostMapping("/addComplaint")
	public Complaint addComplaint(@RequestBody Complaint c,Authentication authentication){
		System.out.println(c.toString());
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
        User U = urep.findByUsername(U1.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		
        
		c.setUser(U);
		/*if(c.getDescription().contains("fight")||c.getDescription().contains("harrassment")
				||c.getDescription().contains("pain")){
			//c.setComplaintType(ComplaintType.Help);
			//return "You might wanna add a file attachment";
			
		}
			else if(c.getDescription().contains("money")){
					c.setComplaintType(ComplaintType.financialComplaint);
					
					comp.addComplaint(c);
					}
		
		//c.setComplaintType(ComplaintType.Help);
					else {
						//c.setUser(U);
					comp.addComplaint(c);}*/
		
		return comp.addComplaint(c);
	}
	
	@PostMapping("/addComplaintAdmin")
	public Complaint addComplaintAdm(@RequestBody Complaint c){
		
		
		
        
		if(c.getDescription().contains("fight")||c.getDescription().contains("harrassment")
				||c.getDescription().contains("pain")){
			//c.setComplaintType(ComplaintType.Help);
			//return "You might wanna add a file attachment";
			
		}
			else if(c.getDescription().contains("money")){
					c.setComplaintType(ComplaintType.financialComplaint);
					
					return comp.addComplaint(c);
					}
		
		//c.setComplaintType(ComplaintType.Help);
					else {
						//c.setUser(U);
					return comp.addComplaint(c);}
		
		return comp.addComplaint(c);
	}
	
	@DeleteMapping("/deleteComplaint/{idComplaint}")
	public void deleteComplaint(@PathVariable long idComplaint){
		comp.deleteComplaint(idComplaint);
	}
	@PutMapping("/updateComplaint")
	public Complaint updateComplaint(@RequestBody Complaint c){
		return comp.UpdateComplaint(c);
		}
	@GetMapping("/getOneComplaint/{idComplaint}")
	public Complaint showComplaint(@PathVariable long idComplaint ){
		return comp.showComplaint(idComplaint)	;	
	}
	@GetMapping("/ShowAllComplaint")
	public List<Complaint> showAllComplaint(){	
		return comp.showAllComplaint();			
	}
	
	@GetMapping("/ShowComplaintType/{type}")
	public List<Integer> retrComplaintsByType(@PathVariable String type){	
	
		return  crep.retComplaintsByType(type);
		
					
	}
	
	
	
	
	
	
	
}
