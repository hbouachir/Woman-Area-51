package tn.esprit.spring.womanarea51.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long idCom;
	    private Timestamp createdate;
	    private String body;
	    private Timestamp createR;
	    private String bodyR;
	    private int countlike;
	    private int countlikeR;
	    
	@ManyToOne
	@JsonIgnore
	private Post postc;
	@ManyToOne
	@JsonIgnore
	private User userc;
	@ManyToOne
	@JsonIgnore
	private User usersc;
	//(cascade=CascadeType.ALL,mappedBy="comment")
	@OneToMany
	@JsonIgnore
	private Set<Like> likes;
	@JsonBackReference 
	@ManyToOne (fetch = FetchType.LAZY)
	private Comment rcomment;
	@JsonManagedReference
	@OneToMany(mappedBy = "rcomment",
			cascade = {CascadeType.ALL }, fetch = FetchType.LAZY)
	private Set<Comment> rcomments;

	
	/*
	@ManyToOne
	@JsonIgnore
	private Comment souscomment;
	//(mappedBy="souscomment",cascade=CascadeType.ALL)
	//(cascade=CascadeType.ALL,mappedBy="souscomment")
	@OneToMany
	@JsonIgnore
	private Set<Comment> reponsemsgs;
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL)
	private Set<Comment> souscomments;
	@JsonIgnore
	@ManyToMany(mappedBy="souscomments",cascade=CascadeType.ALL)
	private Set<Comment> souscommentss;*/
}
