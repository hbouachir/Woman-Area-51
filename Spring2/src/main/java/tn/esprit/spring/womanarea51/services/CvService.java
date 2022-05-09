package tn.esprit.spring.womanarea51.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.womanarea51.entities.Cv;
import tn.esprit.spring.womanarea51.repositories.CvRepository;

@Service
public class CvService implements ICvService {
	@Autowired
    CvRepository cr ;
	@Override
	public Cv addCv(Cv c) {
		// TODO Auto-generated method stub
		return cr.save(c);
	}

	@Override
	public Cv UpdateCv(Cv c) {
		// TODO Auto-generated method stub
		return cr.save(c);
	}

	@Override
	public Cv ShowCv(Long cvId) {
		// TODO Auto-generated method stub
		return cr.findById(cvId).orElse(null);
	}

	@Override
	public List<Cv> ShowAllCv() {
		// TODO Auto-generated method stub
		return (List<Cv>) cr.findAll() ;
	}

	@Override
	public void deleteCv(Long cvId) {
		// TODO Auto-generated method stub
		cr.deleteById(cvId);
	}

}
