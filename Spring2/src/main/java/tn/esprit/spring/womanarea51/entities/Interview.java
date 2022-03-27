package tn.esprit.spring.womanarea51.entities;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	   @EmbeddedId
	   private InterviewKey idInterview ;
	   private String interviewTitle ;
       private String Interviewer ;
	   @Enumerated(EnumType.STRING)
	   private Valid valid ;
	   @JsonFormat(pattern="yyyy-MM-dd")
	   private Date dateInterview;
	   private String urlMeet ;
	   @JsonIgnore
	   @JoinColumn(name = "userId", referencedColumnName = "USER_ID", insertable=false, updatable=false)
	   @ManyToOne
	   User user ;
	   @JsonIgnore
	   @JoinColumn(name = "idOffer", referencedColumnName = "idOffer", insertable=false, updatable=false)
	   @ManyToOne
	   JobOffer jobOffer ;
}
