package tn.esprit.spring.womanarea51;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
@ComponentScan({" tn.esprit.spring.*"})
public class WomanArea51Application {

	// @Autowired
	// private EmailSenderService emailSenderService;
	public static void main(String[] args) {
		SpringApplication.run(WomanArea51Application.class, args);
		
	}
	
	/*
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMain() throws MessagingException {

		emailSenderService.sendMailWithAttachment("moez.mezrigui@esprit.tn",
				"This is email body",				"This is email subject","/D:/3b/Calendrier S2.pdf");

	}
	*/
}

