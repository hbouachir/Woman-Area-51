package tn.esprit.spring.womanarea51.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class event implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long eventId;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date eventDate;
	private String eventLocation;
	@Enumerated(EnumType.STRING)
	private Motive eventMotive;
	@ElementCollection
	List<String> tags = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "event_feedback")
	@JsonIgnore
    Set<feedback> feedbacks;
	
	@ManyToOne
	@JsonIgnore
	private User admin;

}
