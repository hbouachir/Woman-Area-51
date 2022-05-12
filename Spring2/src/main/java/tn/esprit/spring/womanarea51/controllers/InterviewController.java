package tn.esprit.spring.womanarea51.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.Contract;
import tn.esprit.spring.womanarea51.entities.Interview;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.ContractRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.ContractServicempl;
import tn.esprit.spring.womanarea51.services.EmailSenderService;
import tn.esprit.spring.womanarea51.services.InterviewService;
@RestController
@RequestMapping("/api")
public class InterviewController {
	@Autowired
	InterviewService in ;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	UserRepository userRepository;
	/*
    @PostMapping("/addInterview/{idOffer}/{userId}")
    public void addInterview(@RequestBody Interview i , @PathVariable("idOffer") Long idOffer, @PathVariable("userId") Long userId){
        in.addInterview(i, idOffer, userId);
    }
    */
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/PostToOffer/{idOffer}")
	public void PostToOffer(@PathVariable("idOffer") Long idOffer, Authentication authentication){
		User U = userRepository.findByUsername(authentication.getName()).orElse(null);
		in.PostToOffer(idOffer, U.getId());
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/ScheduleInterview/{idOffer}/{userId}")
	public void ScheduleInterview(@RequestBody Interview i , @PathVariable("idOffer") Long idOffer, @PathVariable("userId") Long userId){
		in.UpdateInterview(i, idOffer, userId);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/UpdateInterview/{idOffer}/{userId}")
	public void UpdateInterview(@RequestBody Interview i , @PathVariable("idOffer") Long idOffer, @PathVariable("userId") Long userId){
		in.UpdateInterview(i, idOffer, userId);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/DeleteInterview/{idOffer}/{userId}")
	public void DeleteInterview(@PathVariable("idOffer") Long idOffer, @PathVariable("userId") Long userId){
		in.DelteInterview(idOffer, userId);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/ShowAllInterview")
	public List<Interview> showAllInterview(){
		return in.showAllInterview();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getOneInterview/{idOffer}/{userId}")
	public Interview showOneInterview(@PathVariable("idOffer") Long idOffer, @PathVariable("userId") Long userId){
		return in.showOneInterview(idOffer, userId);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/ListOfAcceptedUser")
	public List<User> ListOfAcceptedUser(){
		return in.ListOfAcceptedUser();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/ListOfRejectedUser")
	public List<User> ListOfRejectedUser(){
		return in.ListOfRejectedUser();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/ListOfPendingUser")
	public List<User> ListOfPendingUser(){
		return in.ListOfPendingUser();
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/ShowInterviewByUser")
	public List<Interview> showInterviewByUser(Authentication authentication){
		User U = userRepository.findByUsername(authentication.getName()).orElse(null);
		return in.listInterviewsParUser(U.getId());
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/AcceptUser/{userId}")
	public void AcceptInterview(@RequestBody Interview i){
		in.generateContract(i);
		//return in.ContractParUser(userId);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/rejectCandidate")
	public void rejectCandidate(@RequestBody Interview i){
		in.rejectUser(i);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/PendCandidate")
	public void  PendCandidate(@RequestBody Interview i){
		in.PendUser(i);

	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/showContract")
	public List<Contract> showContractByUser( Authentication authentication){
		User U = userRepository.findByUsername(authentication.getName()).orElse(null);
		return in.ContractParUser(U.getId());
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/showAllContract")
	public List<Contract> showContractByUser(){
		return  in.showAllContract();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/SendAcceptedmail")
	public void SendAcceptedmail(@RequestParam("toEmail") String toEmail,@RequestParam("attachment") String attachment)throws MessagingException,IOException {
		in.sendMail(toEmail, "Congratulation You Are Accepted Check your Contract", "Women-Area", attachment);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/SendPendingtmail")
	public void SendPendingmail(@RequestParam("toEmail") String toEmail)throws MessagingException,IOException {
		in.sendMailNoAttachement(toEmail,"Pending", "Women-Area");
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/SendRejectedtmail")
	public void SendRejectedmail(@RequestParam("toEmail") String toEmail) throws MessagingException,IOException {
		in.sendMailNoAttachement(toEmail, "Rejected", "Women-Area");
	}

}