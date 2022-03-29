package tn.esprit.spring.womanarea51.entities;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class feedback implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long feedbackId;
	private Float rating;
	private Integer object;
	private Integer organizers;
	private Integer location;
	private Integer addedValue;
	private Integer recommend;
	private Integer service;
	private Integer futureEvents;
	private String comment;
	
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonIgnore
    User participant;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    event event_feedback;
}
