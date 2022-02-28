package tn.esprit.spring.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class event{


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long eventId;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date eventDate;
	private String eventLocation;
	@Enumerated(EnumType.STRING)
	private Motive eventMotive;
	
	@ManyToMany(mappedBy="eventsParticipated", cascade = CascadeType.ALL)
	private Set<User> participants;
	
	@ManyToOne
	private User admin;

}
