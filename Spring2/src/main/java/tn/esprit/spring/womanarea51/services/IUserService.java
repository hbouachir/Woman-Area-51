package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.User;

public interface IUserService {
	User addUser(User u);
	User ShowUser(long id);
	User UpdateUser(User u);
	void DeleteUser(long id);
	List<User> ShowAllUser();
	void addUserAffectRole(long idRole,User u);
	
	
}
