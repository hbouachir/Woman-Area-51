package tn.sirine.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.sirine.spring.entities.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByName(String username);

	User findByNumero(String smsRequest);

}
