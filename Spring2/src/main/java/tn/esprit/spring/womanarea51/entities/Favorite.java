package tn.esprit.spring.womanarea51.entities;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
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
public class Favorite {
		@EmbeddedId
		private FavoriteKey favoriteKey ;
		private String OfferTitle ;
		private String domaine ;
		private String companyName ;
		private String offerPlace ;
		private String salaire ;
	    @Temporal (TemporalType.DATE)
	    @DateTimeFormat(pattern = "MM-dd-yyyy") 
	    private Date offerDeadline;
	    @Enumerated(EnumType.STRING)
	    private TypeContract typeContract ;
	    @Temporal (TemporalType.DATE)
	    @DateTimeFormat(pattern = "MM-dd-yyyy") 
		private Date startDate ;
		@Temporal (TemporalType.DATE)
		@DateTimeFormat(pattern = "MM-dd-yyyy") 
		private Date endDate ;
	
	
	   
	   
	   @JsonIgnore
	   @JoinColumn(name = "userId", referencedColumnName = "USER_ID", insertable=false, updatable=false)
	   @ManyToOne
	   User user ;
	   @JsonIgnore
	   @JoinColumn(name = "idOffer", referencedColumnName = "idOffer", insertable=false, updatable=false)
	   @ManyToOne
	   JobOffer jobOffer ;
}
