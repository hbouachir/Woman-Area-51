package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.fund;
import tn.esprit.spring.repositories.FundRepository;

@Service
public class FundServiceImp implements IFundService {
	@Autowired
	FundRepository FRepository;
	
	@Override
	public void AddFund(fund f) {
		FRepository.save(f);
	}
	
	
	
	@Override
	public fund EditFund(fund f) {
		fund updated = new fund();
		updated=FRepository.save(f);
		return updated;
		
	}
	
	
	
	@Override
	public void DeleteFund(fund f) {
		FRepository.delete(f);
	}
	
	
	@Override
	public fund FindFund(Long id) {
		fund f=new fund();
		f=FRepository.findById(id).get();
		return f;
	}
	
	@Override
	public List<fund> ListFunds() {
		List<fund> list=new ArrayList<fund>() ;
		FRepository.findAll().forEach(f->list.add(f));
		return list;
	}


}
