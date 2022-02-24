package tn.sirine.spring.entities;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
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
	  private Long idPost;
	
	    private String title;
	    private String body;
	    private Timestamp createDate;
	    private String urlImage;
	    
	 @OneToMany
	 private Set<Comment> comments;
	 
	 @ManyToOne
	 private User userp;
	 
	 @OneToMany
	 private Set<Rating> ratings;
	    
}