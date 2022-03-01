package tn.esprit.spring.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpertInterview {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private long idExpertInterview;
	@Temporal (TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy") 
	private Date dateExpertInterview;
	private String expertName;
	private String expertField;
	private Boolean interviewType;
	
	@ManyToOne
	User user;
}
