package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.User;

public interface IUserService {
	User addUser(User u);
	User ShowUser(long id);
	User UpdateUser(User u);
	void DeleteUser(long id);
	List<User> ShowAllUser();

	
	
}