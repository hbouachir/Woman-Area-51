package tn.esprit.spring.womanarea51.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "subscriptions")
public class Subscription {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long idSubscription ;

    String type;

    int amount ;


    @JsonIgnore
    @Nullable
    @OneToMany(mappedBy = "subscription")
    List<Enrollement> enrollements;


}
