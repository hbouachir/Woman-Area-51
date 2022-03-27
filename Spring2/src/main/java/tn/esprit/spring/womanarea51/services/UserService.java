package tn.esprit.spring.womanarea51.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.User;

import tn.esprit.spring.womanarea51.repositories.UserRepository;

@Service
public class UserService implements IUserService {

	
	
	@Autowired
    UserRepository ur;
	
	@Override
	public User addUser(User u) {
		// TODO Auto-generated method stub
		return ur.save(u);
	}

	@Override
	public User ShowUser(long id) {
		// TODO Auto-generated method stub
		return ur.findById(id).orElse(null);
	}

	@Override
	public User UpdateUser(User u) {
		
		return ur.save(u);
	}

	@Override
	public void DeleteUser(long id) {
		ur.deleteById(id);
	}

	@Override
	public List<User> ShowAllUser() {
		// TODO Auto-generated method stub
		return (List<User>) ur.findAll();
	}

	

}