package tn.sirine.spring.entities;

import java.io.Serializable;


import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = 
GenerationType.IDENTITY)
private Long id;
private String userName;
private String email;
private String password;
private String name;
private String lastName;
private String Numero;
private Boolean active;
private int count;
@ManyToMany(cascade = CascadeType.PERSIST, 
fetch = FetchType.EAGER)
private Set<Role> roles;


@OneToMany
private Set<Mensaje> msgenvoi;

//(mappedBy="userto",cascade=CascadeType.ALL)
//(cascade=CascadeType.ALL,mappedBy="userto")
@OneToMany
private Set<Mensaje> msgrecu;
//(mappedBy="userpub",cascade=CascadeType.ALL)
//(cascade=CascadeType.ALL,mappedBy="userpub")

}

