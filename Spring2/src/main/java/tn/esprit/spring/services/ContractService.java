package tn.esprit.spring.services;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Contract;

interface ContractService {

	List<Contract> ContractParUser (Long userId );
}
