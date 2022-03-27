package tn.esprit.spring.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;


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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long userId;
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
	@OneToMany(mappedBy="user")
	private List<Interview> interviews;
	

}
