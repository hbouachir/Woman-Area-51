package tn.esprit.spring.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class fundCategory  {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long categoryId;
	private String categoryType;
	private String categoryImg;
	
	@OneToMany(mappedBy="fCategory")
	private Set<fund> Funds;
	

}
