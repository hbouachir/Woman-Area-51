package tn.esprit.spring.womanarea51.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.Role;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.RoleRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	RoleRepository rr;
	
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

	@Transactional
	@Override
	public void addUserAffectRole(long idRole, User u) {
		Role r=rr.findById(idRole).orElse(null);
		u.setRole(r);
		ur.save(u);
	}


}
