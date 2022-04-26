package tn.esprit.spring.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	String cvURL;
	
	@Nullable
	String imageURL;
	boolean status;
	
	@ManyToOne
	private Role role;
	
@OneToMany
private Set<Post> posts;
@OneToMany
private Set<Message> messages;

@ManyToMany
private Set<User> user1;

}
