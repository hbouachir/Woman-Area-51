package tn.esprit.spring.womanarea51.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.Contract;
import tn.esprit.spring.womanarea51.entities.Interview;
import tn.esprit.spring.womanarea51.services.EmailSenderService;
import tn.esprit.spring.womanarea51.services.InterviewService;

@RestController
public class InterviewController {
@Autowired
InterviewService in ;
@Autowired
private EmailSenderService emailSenderService;
/*
@PostMapping("/addInterview/{idOffer}/{userId}")
public void addInterview(@RequestBody Interview i , @PathVariable("idOffer") Long idOffer, @PathVariable("userId") Long userId){
	in.addInterview(i, idOffer, userId);
}
*/
@PutMapping("/PostToOffer/{idOffer}/{userId}")
public void PostToOffer(@PathVariable("idOffer") Long idOffer, @PathVariable("userId") Long userId){
	in.PostToOffer(idOffer, userId);
}
@PutMapping("/ScheduleInterview/{idOffer}/{userId}")
public void ScheduleInterview(@RequestBody Interview i , @PathVariable("idOffer") Long idOffer, @PathVariable("userId") Long userId){
	in.UpdateInterview(i, idOffer, userId);
}
@PutMapping("/UpdateInterview/{idOffer}/{userId}")
public void UpdateInterview(@RequestBody Interview i , @PathVariable("idOffer") Long idOffer, @PathVariable("userId") Long userId){
	in.UpdateInterview(i, idOffer, userId);
}
@DeleteMapping("/DeleteInterview/{idOffer}/{userId}")
public void DeleteInterview(@PathVariable("idOffer") Long idOffer, @PathVariable("userId") Long userId){
	in.DelteInterview(idOffer, userId);
}
@GetMapping("/ShowAllInterview")
public List<Interview> showAllInterview(){	
	return in.showAllInterview();			
}
@GetMapping("/ListOfAcceptedUser")
public Set<String> ListOfAcceptedUser(){
	return in.ListOfAcceptedUser();
}
@GetMapping("/ListOfRejectedUser")
public Set<String> ListOfRejectedUser(){
	return in.ListOfRejectedUser();
}
@GetMapping("/ListOfPendingUser")
public Set<String> ListOfPendingUser(){
	return in.ListOfPendingUser();
}
@GetMapping("/ShowInterviewByUser/{userId}")
public List<Interview> showInterviewByUser( @PathVariable("userId") Long userId){
	return in.listInterviewsParUser(userId);
}
@PutMapping("/AcceptUser/{userId}")
public List<Contract> AcceptInterview(@RequestBody Interview i, @PathVariable("userId") Long userId){
	 in.generateContract(i);
	 return in.ContractParUser(userId);
}
@GetMapping("/showContract/{userId}")
public List<Contract> showContract( @PathVariable("userId") Long userId){
	return in.ContractParUser(userId);
}
@PostMapping("/SendAcceptedmail")
public void SendAcceptedmail(@RequestParam("toEmail") String toEmail,@RequestParam("attachment") String attachment)throws MessagingException,IOException {
	in.sendMail(toEmail, "Congratulation You Are Accepted Check yout Contract", "Women-Area", attachment);
}
@PostMapping("/SendPendingtmail")
public void SendPendingmail(@RequestParam("toEmail") String toEmail,@RequestParam("attachment") String attachment)throws MessagingException,IOException {
	in.sendMail(toEmail, "Pending", "Women-Area", attachment);
}
@PostMapping("/SendRejectedtmail")
public void SendRejectedmail(@RequestParam("toEmail") String toEmail,@RequestParam("attachment") String attachment)throws MessagingException,IOException {
	in.sendMail(toEmail, "Rejected", "Women-Area", attachment);
}
@PutMapping("/rejectCandidate")
public Set<String> rejectCandidate(@RequestBody Interview i){ 
	in.rejectUser(i);
	return in.ListOfRejectedUser() ;
}
@PutMapping("/PendCandidate")
public Set<String> PendCandidate(@RequestBody Interview i){ 
	in.PendUser(i);
	return in.ListOfPendingUser();
}



}
