package tn.esprit.spring.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import tn.esprit.spring.entities.Contract;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.ContractRepository;
import tn.esprit.spring.services.ContractPDFExporter;
import tn.esprit.spring.services.EmailSenderService;


@RestController
public class ContractController {
	@Autowired
    private EmailSenderService emailSenderService;
	@Autowired
	ContractRepository cr ;
	
	
	    @GetMapping("/pdf/{userId}")
	    public void exportToPDF(HttpServletResponse response,@PathVariable ("userId")Long userId ) throws DocumentException, IOException {
		response.setContentType("application/pdf");
       
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=contract" +  ".pdf";
        response.setHeader(headerKey, headerValue);
        List<Contract> listContracts =(List<Contract>) cr.ContractParUser(userId);
        ContractPDFExporter exporter = new ContractPDFExporter(listContracts);
        exporter.export(response);
         
	}
	/*
	@PostMapping("/email/{toEmail}")
	String sendEmail (@PathVariable ("toEmail") String toEmail) throws MessagingException{
	this.emailSenderService.sendMail(toEmail,
			"You Are Accepted","Women-Area-JobOffer");
			
	return " message sent";

	}*/
}
