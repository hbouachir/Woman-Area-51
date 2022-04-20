package tn.esprit.spring.womanarea51;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.spring.womanarea51.configurations.TwilioConfig;

import javax.annotation.PostConstruct;


@EnableScheduling
@SpringBootApplication
public class WomanArea51Application {

	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void initTwilio(){
		Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
	}

	public static void main(String[] args) {
		SpringApplication.run(WomanArea51Application.class, args);
	}

}
