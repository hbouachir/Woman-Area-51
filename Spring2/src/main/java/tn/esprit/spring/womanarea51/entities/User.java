package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
}