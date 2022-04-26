package tn.esprit.spring;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.esprit.spring.services.EmailSenderService;


@EnableSwagger2
@SpringBootApplication
@ComponentScan({" tn.esprit.spring.*"})
public class WomanAreaApplication {

	// @Autowired
	// private EmailSenderService emailSenderService;
	public static void main(String[] args) {
		SpringApplication.run(WomanAreaApplication.class, args);
		
	}
	
	/*
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMain() throws MessagingException {

		emailSenderService.sendMailWithAttachment("moez.mezrigui@esprit.tn",
				"This is email body",				"This is email subject","/D:/3b/Calendrier S2.pdf");

	}
	*/
}

