package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IRoleService;

@RestController
@RequestMapping("Role")
public class RoleController {
	
	@Autowired 
	IRoleService rs;
	
	@PostMapping("addRole")
	public Role addUser(@RequestBody Role r){
		return rs.addRole(r);
		
		
	}
	
	@DeleteMapping("deleteRole")
	public void deleteRole(@RequestParam("roleId") long roleId){
		rs.DeleteRole(roleId);
		
	}
	
	@PutMapping("updateRole")
	public Role updateRole(@RequestBody Role r){
		return rs.UpdateRole(r);
		}
		
		
	
	
	
	@GetMapping("{userid}")
	public Role showRole(@PathVariable long roleId ){
		
		return rs.ShowRole(roleId);		
		
	}
	
	
	@GetMapping("/showAllRole")
	public List<Role> showAllRole(){
		
		return rs.ShowAllRole();		
		
	}

}
