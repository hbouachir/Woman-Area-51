package tn.esprit.spring.womanarea.demo.Services;

import tn.esprit.spring.womanarea.demo.Entities.Role;

import java.util.List;

public interface IRoleService {
	Role addRole(Role u);
	Role ShowRole(long id);
	Role UpdateRole(Role u);
	void DeleteRole(long id);
	List<Role> ShowAllRole();
	void initRoles();
	
}
