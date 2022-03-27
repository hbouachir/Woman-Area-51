package tn.esprit.spring.womanarea51.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.fund;
import tn.esprit.spring.womanarea51.repositories.FundRepository;

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
	
	
	public float estimatedAmountPerYear(int year) {
		float n=0;
		List<fund> funds= new LinkedList<>();
		ListFunds().forEach(f->funds.add(f));
		Iterator<fund> iterator = funds.listIterator();
		Calendar cal = Calendar.getInstance();
		while (iterator.hasNext()) {
			cal.setTime(iterator.next().getLastDonation());
			if (cal.get(Calendar.YEAR)==year)
				n+=iterator.next().getRaised();
		}
		return n;
	}
	
	
	public float estimatedAmountforThisYear() {
		float n=0;
		Calendar cal = Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int months=cal.get(Calendar.MONTH);
		System.out.println(year);
		n=estimatedAmountPerYear(year);
		return n/months*12;
	}
	

}
