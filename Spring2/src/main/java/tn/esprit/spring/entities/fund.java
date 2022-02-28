package tn.esprit.spring.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

public class fund {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long fundId;
	private float goal;
	private float raised;
	private String fundDescription;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastDonation;
	private String benficiaries;
	
	
	@ManyToOne
	private fundCategory fCategory;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Fund")
	private Set<donation> donations;
	
	
	
}
