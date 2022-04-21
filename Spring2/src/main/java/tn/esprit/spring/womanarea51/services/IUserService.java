package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.User;

import java.util.List;

public interface IUserService {
	User save(User u);

	User findOne(long id);
	User updateUser(User u);
	void deleteUser(long id);

	List<User> findAll();
	void addUserAffectRole(long idRole, long idUser) ;
	
	
}
