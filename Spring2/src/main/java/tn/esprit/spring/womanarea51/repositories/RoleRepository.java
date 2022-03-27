package tn.esprit.spring.womanarea51.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.womanarea51.entities.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
