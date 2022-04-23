package tn.esprit.spring.womanarea51.entities;


import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class fundCategory implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long categoryId;
	private String categoryType;
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="fCategory")
	private Set<fund> Funds;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "fc")
	FundCategoryFile file;
	

}
