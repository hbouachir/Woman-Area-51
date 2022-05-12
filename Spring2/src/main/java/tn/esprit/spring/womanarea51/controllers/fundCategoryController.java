package tn.esprit.spring.womanarea51.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.fundCategory;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IFundCategoryService;


@RestController
public class fundCategoryController {
	
	@Autowired
	IFundCategoryService IFCS;
	
	@Autowired 
	UserRepository UR;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add-fund-category")
	void AddFundCat(@RequestBody fundCategory fc, Authentication authentication){
		User U=UR.findByUsername(authentication.getName()).orElse(null);
		System.out.println("**********************************"+U.getId());
		IFCS.AddFundCat(fc);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/fund-category/Update")
	fundCategory EditFundCat(@RequestBody fundCategory fc,Authentication authentication) {
		
		return IFCS.EditFundCat(fc);
				
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/remove-fund-category/{fundCatId}")
	void RemoveFund(@PathVariable("fundCatId") Long fundCatId,Authentication authentication) {
		fundCategory fc=IFCS.FindFundCat(fundCatId);
		System.out.println(fc.toString());
		IFCS.DeleteFundCat(fc);
	}
	
	
	@GetMapping("/find-fund-category/{id-fundCat}")
	fundCategory FindFundCatById(@PathVariable("id-fundCat") Long fundCatId) {
		return IFCS.FindFundCat(fundCatId);
	}
	
	
	@GetMapping("/find-all-fund-categorys")
	List<fundCategory> FindAllfundCats() {
		return IFCS.ListFundCat();
	}
	
	@GetMapping("/find-fund-categories-by-Type/{type}")
	public List<fundCategory> ListCatByType(@PathVariable("type")String type){
		return IFCS.ListCatByType(type);
	}
	
}
