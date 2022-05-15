package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PublicComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPublicComplaint;
    @NotNull
    String fullName;
    @NotNull
    Long phoneNumber;
    @NotNull
    String location;

    String description;

}
