package tn.esprit.spring.entities;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	   //private static final long serialVersionUID = 4L ;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long idInterview ;
	  private String interviewTitle ;
      private String Interviewer ;
	   @Enumerated(EnumType.STRING)
	   private Valid valid ;
	   @JsonFormat(pattern="yyyy-MM-dd")
	   private Date dateInterview; 
	   @JsonIgnore
	   //@JoinColumn(name = "userId", referencedColumnName = "userId", insertable=false, updatable=false)
	   @ManyToOne
	   User user ;
	   @JsonIgnore
	   //@JoinColumn(name = "idOffer", referencedColumnName = "idOffer", insertable=false, updatable=false)
	   @ManyToOne
	   JobOffer jobOffer ;
}
