package tn.esprit.spring.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class donation  {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long donationId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date donationDate;
	private float amount;
	@Enumerated(EnumType.STRING)
	private Method payMethod;
	
	//@ManyToOne
	//User user;
	
	@ManyToOne
	fund Fund;
	
	
	
	
	
	

}
