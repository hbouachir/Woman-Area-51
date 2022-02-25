package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;

public interface IRoleService {
	Role addRole(Role u);
	Role ShowRole(long id);
	Role UpdateRole(Role u);
	void DeleteRole(long id);
	List<Role> ShowAllRole();
	
}
