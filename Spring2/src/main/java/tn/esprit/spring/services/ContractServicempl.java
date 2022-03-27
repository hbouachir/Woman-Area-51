package tn.esprit.spring.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contract;
import tn.esprit.spring.repositories.ContractRepository;

@Service
public class ContractServicempl implements ContractService {

	@Autowired
	ContractRepository cr ;

	@Override
	public List<Contract> ContractParUser(Long userId) {
		// TODO Auto-generated method stub
		return cr.ContractParUser(userId);
	}
	
	
	

}
