package tn.esprit.spring.womanarea51.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.VerificationToken;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

    VerificationToken findByToken(String token);
 
    VerificationToken findByUser(User user);

}
