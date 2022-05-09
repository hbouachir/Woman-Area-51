package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.User;

import javax.transaction.Transactional;
import java.util.List;

public interface IUserService {
	User save(User u);

	User findOne(long id);
	User updateUser(User u);

    @Transactional
    void addUserAffectRole(long idRole, long idUser);

    void deleteUser(long id);

	List<User> findAll();

	

	User findByUsername(String username);


}
