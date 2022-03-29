package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


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
	private Long id;



	@Column(name = "FIRST_NAME")
	private String firstName;

	private String stripe_id=null;
	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "username")
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
	
	

}
