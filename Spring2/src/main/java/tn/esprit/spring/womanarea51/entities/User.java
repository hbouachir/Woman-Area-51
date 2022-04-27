package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Builder
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	@JsonView(CourseView.Less.class)
	private Long id;



	@Column(name = "FIRST_NAME")
	@JsonView(CourseView.Less.class)
	private String firstName;

	private String stripe_id=null;
	@Column(name = "LAST_NAME")
	@JsonView(CourseView.Less.class)
	private String lastName;

	@Column(name = "username")
	@JsonView(CourseView.Less.class)
	private String username;

	@Column(name = "password")
	private String password;

	@Transient
	private String passwordConfirm;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateN;

	@Column(name = "tel")
	private String tel;
	private int loginTime=0;
	private int pointFidelite = 0;

	private int pointWord = 0;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public void  addRole(Role role){
		this.roles.add(role);
	}

	@Column(name = "sexe")
	@Enumerated(EnumType.STRING)
	private Sexe sexe;

	@Column(name = "EtatAcc")
	private Boolean EtatAcc = true;

	@Column(name = "enabled")
	private boolean enabled = false;



	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate signupDay = LocalDate.now();

	@OneToMany(cascade=CascadeType.ALL)
	List<Subscription> subscriptions;


	@OneToMany(mappedBy = "user")
	List<Enrollement> enrollements;
	

	@OneToMany(cascade=CascadeType.ALL, mappedBy="expert")
	@JsonIgnore
	Set<ExpertInterview> expertinterviews;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	@JsonIgnore
	Set<Complaint> complaints;


	@OneToOne(mappedBy = "u")
	userFile file;

	public User(String firstName, String lastName, String username, String password, String passwordConfirm, String email, String address, Date dateN, String tel, int loginTime, int pointFidelite, Set<Role> roles, Sexe sexe, Boolean etatAcc, boolean enabled, LocalDate signupDay) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.email = email;
		this.address = address;
		this.dateN = dateN;
		this.tel = tel;
		this.loginTime = loginTime;
		this.pointFidelite = pointFidelite;
		this.roles = roles;
		this.sexe = sexe;
		EtatAcc = etatAcc;
		this.enabled = enabled;
		this.signupDay = signupDay;
	}

	public User(String username, String email, String password, String firstName, String lastName, String address,
				Date dateN, String tel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.dateN = dateN;
		this.tel = tel;
	}

	public User(String firstName, String stripe_id, String lastName, String username, String password, String passwordConfirm, String email, String address, Date dateN, String tel, int loginTime, int pointFidelite, Set<Role> roles, Sexe sexe, Boolean etatAcc, boolean enabled, LocalDate signupDay) {
		this.firstName = firstName;
		this.stripe_id = stripe_id;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.email = email;
		this.address = address;
		this.dateN = dateN;
		this.tel = tel;
		this.loginTime = loginTime;
		this.pointFidelite = pointFidelite;
		this.roles = roles;
		this.sexe = sexe;
		EtatAcc = etatAcc;
		this.enabled = enabled;
		this.signupDay = signupDay;
	}

	public User(String username, String email, String password, String firstName, String lastName, String address,
				Date dateN, String tel, Sexe sexe) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.dateN = dateN;
		this.tel = tel;
		this.sexe = sexe;
	}
	



	public User(String username, String email, String password, String firstName, String lastName, String address,
			String tel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.tel = tel;

	}

	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	@ManyToMany(cascade=CascadeType.ALL)
	public Set<JobOffer> jobOffers ;

	@OneToMany(mappedBy = "student")
	Set<Class> classes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="instructor")
	private Set<Course> courses;

	@OneToMany(mappedBy="user")
	private List<Interview> interviews;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	@JsonIgnore
	private Set<donation> donations;
	

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "participant")
	@JsonIgnore
    Set<feedback> participant_feedbacks;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="admin")
	@JsonIgnore
	private Set<event> eventsMonitered;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<fund> Funds;

	@OneToMany
	private Set<Post> posts;

	@OneToMany
	private Set<Comment> comments;

	@OneToMany
	private Set<Comment> souscomment;

	@OneToMany
	private Set<Pub> pubs;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + pointWord;
		result = prime * result + ((posts == null) ? 0 : posts.hashCode());
		result = prime * result + ((pubs == null) ? 0 : pubs.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((sexe == null) ? 0 : sexe.hashCode());
		result = prime * result + ((souscomment == null) ? 0 : souscomment.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pointWord != other.pointWord)
			return false;
		if (posts == null) {
			if (other.posts != null)
				return false;
		} else if (!posts.equals(other.posts))
			return false;
		if (pubs == null) {
			if (other.pubs != null)
				return false;
		} else if (!pubs.equals(other.pubs))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (sexe != other.sexe)
			return false;
		if (souscomment == null) {
			if (other.souscomment != null)
				return false;
		} else if (!souscomment.equals(other.souscomment))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
