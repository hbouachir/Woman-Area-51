package tn.esprit.spring.womanarea.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea.demo.Entities.Subscription;
import tn.esprit.spring.womanarea.demo.Entities.User;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription>  findByType(String type);

}
