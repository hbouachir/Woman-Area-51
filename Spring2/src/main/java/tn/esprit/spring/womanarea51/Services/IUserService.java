package tn.esprit.spring.womanarea51.Services;

import tn.esprit.spring.womanarea51.Entities.User;

import java.util.List;

public interface IUserService {
	User save(User u);

	User findOne(long id);
	User updateUser(User u);
	void deleteUser(long id);

	List<User> findAll();
	void addUserAffectRole(long idRole,User u);
	
	
}
