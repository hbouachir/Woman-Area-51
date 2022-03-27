package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.fundCategory;

public interface IFundCategoryService {
	
	void AddFundCat(fundCategory c);
	
	fundCategory EditFundCat(fundCategory c);
	
	void DeleteFundCat(fundCategory c);
	
	

	fundCategory FindFundCat(Long id);
	
	List<fundCategory> ListFundCat();
}
