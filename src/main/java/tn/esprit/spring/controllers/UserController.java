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

import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IRoleService;
import tn.esprit.spring.services.IUserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	IRoleService rs;
	@Autowired 
	IUserService us;
	
	@PostMapping("addUser")
	public User addUser(@RequestBody User u){
		return us.addUser(u);
		
		
	}
	
	@DeleteMapping("deleteUser")
	public void deleteUser(@RequestParam("userId") long userId){
		us.DeleteUser(userId);
		
	}
	
	@PutMapping("updateUser")
	public User updateUser(@RequestBody User u){
		
		return us.UpdateUser(u);
		}
		
		
	
	
	
	@GetMapping("{userid}")
	public User showUser(@PathVariable long userid ){
		
		return us.ShowUser(userid);		
		
	}
	
	
	@GetMapping
	public List<User> showAllUser(){
		
		return us.ShowAllUser();		
		
	}
	
	@PostMapping("addUserAffectRole")
	public void addUserAffectRole(@RequestParam("idRole") long idRole, @RequestBody User u) {
		us.addUserAffectRole(idRole, u);
	}
	
	
}
