package tn.esprit.spring.womanarea51.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class ExpertInterview implements Serializable {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private long idExpertInterview;
	@Temporal (TemporalType.DATE)
	private Date dateExpertInterview;
	private String expertField;
	private Boolean interviewType;
	private int rating;
	
	/*List<User> searchExperts(List<User> users){
		//List<User> experts = null;
		for (int i=0;i<users.size();i++){
			if(users.get(i).getRole().getDescription()!="expert")
				users.remove(i);			
		}
		return users;
	}
	
	User ExpertToAffect(List<User> experts,String field){
		User expertt = null ;
		for (int i=0;i<experts.size();i++){
			if(experts.get(i).getRole().getExpertField()==field){
				return expertt=experts.get(i);
			}
		}
		
		return expertt;
	}
	
	int countInterviews(User expert){
		
		return expert.getExpertinterviews().size();
	}*/

	@ManyToOne
	User user;
	@ManyToOne
	User expert;
}
