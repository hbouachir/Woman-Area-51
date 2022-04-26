package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FundCategoryFile {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    Long fileId;

	    String fileName;

	    String filePath;
	    @Temporal(TemporalType.TIMESTAMP)
	    Date uploadDate;

	    @JsonIgnore
	    @OneToOne
	    fundCategory fc;
}
