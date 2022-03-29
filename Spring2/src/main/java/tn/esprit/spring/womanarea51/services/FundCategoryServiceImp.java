package tn.esprit.spring.womanarea51.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.fund;
import tn.esprit.spring.womanarea51.entities.fundCategory;

import tn.esprit.spring.womanarea51.repositories.FundCategoryRepository;

@Service
public class FundCategoryServiceImp implements IFundCategoryService {
	
	@Autowired
    FundCategoryRepository FCatRepository;
	
	
	public void AddFundCat(fundCategory c) {
		FCatRepository.save(c);
	}
	
	
	
	public fundCategory EditFundCat(fundCategory c) {
		fundCategory updated = new fundCategory();
		updated=FCatRepository.save(c);
		return updated;
		
	}
	
	
	
	public void DeleteFundCat(fundCategory c) {
		FCatRepository.delete(c);
	}
	
	
	
	public fundCategory FindFundCat(Long id) {
		fundCategory c=new fundCategory();
		c=FCatRepository.findById(id).get();
		return c;
	}
	
	
	public List<fundCategory> ListFundCat() {
		List<fundCategory> list=new ArrayList<fundCategory>() ;
		FCatRepository.findAll().forEach(c->list.add(c));
		return list;
	}
	
	
		
	


}
