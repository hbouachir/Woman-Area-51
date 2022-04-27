package tn.esprit.spring.womanarea51.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.womanarea51.entities.Enrollement;

@Repository
public interface EnrollementRepository extends JpaRepository<Enrollement,Long> {
// SHow valid enrollement (not expired)

    @Query(value = "SELECT * FROM `enrollement` WHERE `user_id` LIKE ?1% AND `Expire`>=CURRENT_DATE LIMIT 1", nativeQuery = true)
    public Enrollement getEnrollementValidByUser(long cle);
}
