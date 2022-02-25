package tn.esprit.spring.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long fileId;

    String fileName;

    String filePath;
    @Temporal(TemporalType.DATE)
    Date uploadDate;

}
