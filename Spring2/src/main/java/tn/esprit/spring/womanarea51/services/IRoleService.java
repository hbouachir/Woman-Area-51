package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.Role;

public interface IRoleService {
	Role addRole(Role u);
	Role ShowRole(long id);
	Role UpdateRole(Role u);
	void DeleteRole(long id);
	List<Role> ShowAllRole();
	
}
