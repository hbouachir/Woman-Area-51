package tn.esprit.spring.womanarea51.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Filepub {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    Long fileId;

	    String fileName;

	    String filePath;
	    @Temporal(TemporalType.TIMESTAMP)
	    Date uploadDate;
	    
	    @JsonIgnore
	    @ManyToOne
	    Pub pub;
}
