package tn.esprit.spring.womanarea51;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.esprit.spring.womanarea51.configurations.TwilioConfig;
import tn.esprit.spring.womanarea51.entities.ERole;
import tn.esprit.spring.womanarea51.entities.Role;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.RoleRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@EnableScheduling
@SpringBootApplication
public class WomanArea51Application {

	@Autowired
	private TwilioConfig twilioConfig;

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired

	PasswordEncoder encoder;

	@PostConstruct
	public void initTwilio() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}


	public static void main(String[] args) {
		SpringApplication.run(WomanArea51Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

		};

	}


}
