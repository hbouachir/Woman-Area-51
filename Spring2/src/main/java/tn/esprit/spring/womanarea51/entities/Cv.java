package tn.esprit.spring.womanarea51.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Cv implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long cvId;
	String linkedIn;
	String cvPDF ;
	@OneToOne
	private User user ;
}
