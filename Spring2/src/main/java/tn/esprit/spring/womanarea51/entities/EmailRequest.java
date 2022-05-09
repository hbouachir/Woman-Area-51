package tn.esprit.spring.womanarea51.entities;

import java.time.LocalDateTime;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {
	
	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String subject;
	
	@NotEmpty
	private String body;
	
	@NotNull
	private LocalDateTime dateTime;
/*	
	@NotNull
	private ZoneId timeZone;
*/	
	
	

}
