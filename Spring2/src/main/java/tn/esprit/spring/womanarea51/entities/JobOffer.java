
package tn.esprit.spring.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
    // associations

    @JsonIgnore
    @OneToMany(mappedBy="jobOffer")
	private List<Interview> interviews;

}
