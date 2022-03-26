package tn.esprit.spring.womanarea51.entities;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Interview {
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Id
		private long idInterview ;
		private String interviewTitle ;
		private String interviewer ;
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(pattern = "MM-dd-yyyy") 
		private Date dateInterview ;  
		@Enumerated(EnumType.STRING)
		private Valid valid ;
		// associations
		@JsonIgnore  
		@ManyToOne
		JobOffer jobOffer ;
		@JsonIgnore
		@ManyToOne
		User user ;
}
