package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContractKey implements Serializable {

	private long userId ;
	private long idOffer ;
}
