package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entities.fund;
import tn.esprit.spring.services.IDonationService;
import tn.esprit.spring.services.IFundService;

@RestController
public class fundController {
	
	@Autowired
	IDonationService IDS;
	@Autowired
	IFundService IFS;
	
	@PostMapping("/propose-fund")
	void AddFund(@RequestBody fund f){
		IFS.AddFund(f);
		
	}
	
	@PutMapping("/fund/Update")
	fund EditFund(@RequestBody fund f) {
		
		return IFS.EditFund(f);
				
	}
	
	@DeleteMapping("/remove-fund/{fundId}")
	@ResponseBody
	void RemoveFund(@PathVariable("fundId") Long fundId) {
		fund f=IFS.FindFund(fundId);
		IFS.DeleteFund(f);
	}
	
	
	@GetMapping("/find-fund/{id-fund}")
	fund FindFundById(@PathVariable("id-fund") Long fundId) {
		return IFS.FindFund(fundId);
	}
	
	
	@GetMapping("/find-all-funds")
	List<fund> FindAllfunds() {
		return IFS.ListFunds();
	}
	
	@GetMapping("/find-fund-amount-collected-by-year/{year}")
	float amountCollectedPerYear(@PathVariable("year")int year) {
		return IFS.estimatedAmountPerYear(year);
	}
	
	@GetMapping("/find-fund-amount-estimated-this-year")
	float amountEstimatedThisYear() {
		return IFS.estimatedAmountforThisYear();
	}
	
	

}
