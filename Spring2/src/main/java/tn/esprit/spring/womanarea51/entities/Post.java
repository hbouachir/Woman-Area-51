package tn.esprit.spring.womanarea51.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long id;
	private Timestamp createdate;
	    private String title;
	    private String body;
	    @ElementCollection
	    private List<String> tags = new ArrayList<>();
	    private String image;
	    private Float score=(float) 0;
	    private int countCom;
	    private boolean historypost = false;
	   // private int countrate;
	    
		@ElementCollection(targetClass = RatePub.class)
		@Enumerated(EnumType.STRING)
		private Map<Long,RatePub> ratingPub;  
	    ////
	 /*   @ElementCollection(targetClass = RatePub.class)
		
		private Map<Long,String> ratingPub;*/
	 @ManyToOne
	   private User userp;
	//(mappedBy="postr",cascade=CascadeType.ALL) 
	 //(cascade=CascadeType.ALL,mappedBy="postr")
	
	 @OneToMany(cascade = CascadeType.ALL)
		private Set<Comment> comments;
	
	 
}
