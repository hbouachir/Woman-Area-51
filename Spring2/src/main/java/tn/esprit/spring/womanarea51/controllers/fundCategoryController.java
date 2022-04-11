package tn.esprit.spring.womanarea51.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.womanarea51.entities.fundCategory;
import tn.esprit.spring.womanarea51.services.IFundCategoryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class fundCategoryController {
	
	@Autowired
	IFundCategoryService IFCS;

	@PostMapping("/add-fund-category")
	void AddFundCat(@RequestBody fundCategory fc){
		IFCS.AddFundCat(fc);
		
	}
	
	@PutMapping("/fund-category/Update")
	fundCategory EditFundCat(@RequestBody fundCategory fc) {
		
		return IFCS.EditFundCat(fc);
				
	}
	
	@DeleteMapping("/remove-fund-category/{fundCatId}")
	void RemoveFund(@PathVariable("fundCatId") Long fundCatId) {
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
