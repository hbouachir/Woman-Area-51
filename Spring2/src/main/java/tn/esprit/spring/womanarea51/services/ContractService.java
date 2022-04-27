package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.Contract;

interface ContractService {

	List<Contract> ContractParUser (Long userId );
	List<Contract> showAllContract();
}
