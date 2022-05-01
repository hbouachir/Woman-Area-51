package tn.esprit.spring.womanarea51.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"fCategory","referenceList"})
public class fund implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long fundId;
	private String fundName;
	private float goal;
	private float raised;
	private String fundDescription;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastDonation;
	private String beneficiaries;
	@ElementCollection
	List<String> tags = new ArrayList<>();
	
	
	@ManyToOne
	private fundCategory fCategory;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Fund")
	private Set<donation> donations;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "f")
	Set<FundFiles> files;
	
	
	
}
