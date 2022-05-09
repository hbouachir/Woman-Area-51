package tn.esprit.spring.womanarea51.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long fileId;

    String fileName;

    String filePath;
    @Temporal(TemporalType.TIMESTAMP)
    Date uploadDate;

    @JsonIgnore
    @ManyToOne
    Attachment attachment;
    

}
