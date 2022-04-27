package tn.esprit.spring.womanarea51.entities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	
	String expertField;

	public String getExpertField() {
		return expertField;
	}

	public void setExpertField(String expertField) {
		this.expertField = expertField;
	}

	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}