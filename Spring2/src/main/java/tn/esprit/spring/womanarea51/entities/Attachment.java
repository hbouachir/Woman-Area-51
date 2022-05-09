package tn.esprit.spring.womanarea51.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Attachment {
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Id
private long idAttachment;
private String url;

@ManyToOne
Complaint complaint;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "attachment")
Set<FileComplaint> files;
}
