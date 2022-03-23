package tn.esprit.spring.womanarea.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.womanarea.demo.Entities.User;
import tn.esprit.spring.womanarea.demo.Entities.VerificationToken;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

    VerificationToken findByToken(String token);
 
    VerificationToken findByUser(User user);

}
