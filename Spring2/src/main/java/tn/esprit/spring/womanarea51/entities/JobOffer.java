package tn.esprit.spring.womanarea51.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class JobOffer {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long idOffer ;
	private String domaine ;
	private String companyLogo ;
	private String description ;
	private String offerPlace ;
    @Temporal (TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-yyyy") 
    private Date offerDeadline;
    // associations
    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL,mappedBy="jobOffers")
    public Set<User> users ;   
   
   
}
