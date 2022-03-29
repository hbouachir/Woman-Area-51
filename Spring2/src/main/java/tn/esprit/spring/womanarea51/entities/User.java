package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;




import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "user")
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
	




	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEtatAcc() {
		return EtatAcc;
	}

	public void setEtatAcc(Boolean etatAcc) {
		EtatAcc = etatAcc;
	}


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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateN() {
		return dateN;
	}

	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	




	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;



	}

	public String getStripe_id() {
		return stripe_id;
	}

	public void setStripe_id(String stripe_id) {
		this.stripe_id = stripe_id;
	}

	public int getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(int loginTime) {
		this.loginTime = loginTime;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

	public User() {
		super();

	}

	public User(Long iduser) {
		super();
		this.id = iduser;

	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}


	public int getPointFidelite() {
		return pointFidelite;
	}

	public void setPointFidelite(int pointFidelite) {
		this.pointFidelite = pointFidelite;
	}





	public LocalDate getSignupDay() {
		return signupDay;
	}

	public void setSignupDay(LocalDate signupDay) {
		this.signupDay = signupDay;
	}

	public void  addRole(Role role){
		this.roles.add(role);
	}

	//Ramzi
	@ManyToMany(cascade=CascadeType.ALL)
    public Set<JobOffer> jobOffers ;

	@OneToMany(mappedBy = "student")
	Set<Class> classes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="instructor")
	private Set<Course> courses;

	public Set<Class> getClasses() {
		return classes;
	}
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


	public Set<donation> getDonations() {
		return donations;
	}

	public void setDonations(Set<donation> donations) {
		this.donations = donations;
	}

	public Set<feedback> getParticipant_feedbacks() {
		return participant_feedbacks;
	}
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

	public int getPointWord() {
		return pointWord;
	}

	public void setPointWord(int pointWord) {
		this.pointWord = pointWord;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Comment> getSouscomment() {
		return souscomment;
	}

	public void setSouscomment(Set<Comment> souscomment) {
		this.souscomment = souscomment;
	}

	public Set<Pub> getPubs() {
		return pubs;
	}

	public void setPubs(Set<Pub> pubs) {
		this.pubs = pubs;
	}
	
	

	public void setParticipant_feedbacks(Set<feedback> participant_feedbacks) {
		this.participant_feedbacks = participant_feedbacks;
	}

	public Set<event> getEventsMonitered() {
		return eventsMonitered;
	}

	public void setEventsMonitered(Set<event> eventsMonitered) {
		this.eventsMonitered = eventsMonitered;
	}

	public Set<fund> getFunds() {
		return Funds;
	}

	public void setFunds(Set<fund> funds) {
		Funds = funds;
	}



	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}
