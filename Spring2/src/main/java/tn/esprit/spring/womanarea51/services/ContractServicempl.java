package tn.esprit.spring.womanarea51.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.Contract;
import tn.esprit.spring.womanarea51.repositories.ContractRepository;

@Service
public class ContractServicempl implements ContractService {

	@Autowired
    ContractRepository cr ;

	@Override
	public List<Contract> ContractParUser(Long userId) {
		// TODO Auto-generated method stub
		return cr.ContractParUser(userId);
	}

	@Override
	public List<Contract> showAllContract() {
		// TODO Auto-generated method stub
		return (List<Contract>) cr.findAll();
	}
	
	
	

}
