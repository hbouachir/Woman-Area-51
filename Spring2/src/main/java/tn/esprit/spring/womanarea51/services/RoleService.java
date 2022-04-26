package tn.esprit.spring.womanarea51.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.Role;
import tn.esprit.spring.womanarea51.repositories.RoleRepository;


@Service
public class RoleService implements IRoleService {

	
	@Autowired 
	RoleRepository rr;
	
	@Override
	public Role addRole(Role r) {
		// TODO Auto-generated method stub
		return rr.save(r);
	}

	@Override
	public Role ShowRole(long id) {
		// TODO Auto-generated method stub
		return rr.findById(id).orElse(null);
	}

	@Override
	public Role UpdateRole(Role r) {
		// TODO Auto-generated method stub
		return rr.save(r);
	}

	@Override
	public void DeleteRole(long id) {
		rr.deleteById(id);
	}

	@Override
	public List<Role> ShowAllRole() {
		// TODO Auto-generated method stub
		return (List<Role>) rr.findAll();
	}

}
