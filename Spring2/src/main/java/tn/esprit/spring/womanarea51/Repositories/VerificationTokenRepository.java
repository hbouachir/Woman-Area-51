package tn.esprit.spring.womanarea51.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.womanarea51.Entities.User;
import tn.esprit.spring.womanarea51.Entities.VerificationToken;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

    VerificationToken findByToken(String token);
 
    VerificationToken findByUser(User user);

}
