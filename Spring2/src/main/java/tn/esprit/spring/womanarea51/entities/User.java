package tn.esprit.spring.womanarea51.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;


import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class User {
	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long userId;
	String firstName;
	String lastName;
	String email;
	String userName;
	String password;
    @JsonFormat(pattern="yyyy-MM-dd")
	Date dateOfBirth;
	@Nullable
	String ongName;
	@Nullable
	String field;
	@Nullable
	String address;
	long phoneNumber;
	@Nullable
	String cvURL;
	
	@Nullable
	String imageURL;
	boolean status;
	
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "user_roles", 
//	joinColumns = @JoinColumn(name = "userId"), 
//	inverseJoinColumns = @JoinColumn(name = "roleId"))
//	Set<Role> roles = new HashSet<>();
//	
	@ManyToOne
	Role role;

	@ManyToMany(cascade=CascadeType.ALL)
    public Set<JobOffer> jobOffers ;

	@OneToMany(mappedBy = "student")
	Set<Class> classes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="instructor")
	private Set<Course> courses;
	

}
