package tn.esprit.spring.womanarea51.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.event;
import tn.esprit.spring.womanarea51.entities.fund;
import tn.esprit.spring.womanarea51.entities.fundCategory;
import tn.esprit.spring.womanarea51.repositories.FundCategoryRepository;
import tn.esprit.spring.womanarea51.repositories.FundRepository;

@Service
public class FundServiceImp implements IFundService {
	@Autowired
    FundRepository FRepository;
	
	@Autowired
	FundCategoryRepository FCRepository;
	
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
		fund f=FRepository.findById(id).get();
		
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
	
	public List<fund> FindByCatgeory(fundCategory cat){
		
		List<fund> list=new ArrayList<fund>() ;
		FRepository.findAll().forEach(f->{
			if (f.getFCategory()==cat)
				list.add(f);
		});
		return list;
		
	}
	public List<fund>FindByTags(String tags){
		
		List<String> tagsList=new ArrayList<String>(Arrays.asList(tags.split("#")));
		List<fund> list=new ArrayList<fund>() ;
		tagsList.forEach(e->list.addAll(FRepository.retrieveByTag(e)));
		new LinkedHashSet<>(list);
		List<fund> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(list));
		return listWithoutDuplicates;
		
		
		
	}
}
