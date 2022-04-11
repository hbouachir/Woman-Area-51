package tn.esprit.spring.womanarea51.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.fund;
import tn.esprit.spring.womanarea51.entities.fundCategory;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IDonationService;
import tn.esprit.spring.womanarea51.services.IFundCategoryService;
import tn.esprit.spring.womanarea51.services.IFundService;
import tn.esprit.spring.womanarea51.services.IUserService;

@RestController
public class fundController {
	
	@Autowired
    IDonationService IDS;
	
	@Autowired
    IFundService IFS;
	
	@Autowired
	IFundCategoryService IFCS;
	
	@Autowired
	IUserService IUS;
	
	@Autowired
	UserRepository UR;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/propose-fund/{catid}")
	void AddFund(@RequestBody fund f,@PathVariable("catid")Long catId,Authentication authentication){
		//System.out.println(catId);
		
		f.setFCategory(IFCS.FindFundCat(catId));
		IFS.AddFund(f);
		
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/fund/Update/{catid}")
	fund EditFund(@RequestBody fund f,@PathVariable("catid")Long catId, Authentication authentication) {
		f.setFCategory(IFCS.FindFundCat(catId));
		return IFS.EditFund(f);
				
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/remove-fund/{fundId}")
	void RemoveFund(@PathVariable("fundId") Long fundId, Authentication authentication) {
		fund f=IFS.FindFund(fundId);
		IFS.DeleteFund(f);
	}
	
	
	@GetMapping("/find-fund/{id-fund}")
	fund FindFundById(@PathVariable("id-fund") Long fundId) {
		return IFS.FindFund(fundId);
	}
	
	
	@GetMapping("/find-funds-by-category/{cat}")
	List<fund> findByCat(@PathVariable("cat") Long id){
		fundCategory cat=IFCS.FindFundCat(id);
		System.out.println(cat.toString());
		return IFS.FindByCatgeory(cat);
	}
	
	@GetMapping("/find-all-funds")
	List<fund> FindAllfunds() {
		return IFS.ListFunds();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/find-fund-amount-collected-by-year/{year}")
	float amountCollectedPerYear(@PathVariable("year")int year, Authentication authentication) {
		return IFS.estimatedAmountPerYear(year);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/find-fund-amount-estimated-this-year")
	float amountEstimatedThisYear(Authentication authentication) {
		return IFS.estimatedAmountforThisYear();
	}
	
	@GetMapping("/find-funds-by-tags/{tags}")
	List<fund> FindFundsByTags(@PathVariable("tags") String tags){
		return IFS.FindByTags(tags);
	}
	
	

}
