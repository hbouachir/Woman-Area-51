package tn.esprit.spring.womanarea51.services;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.eventFile;

public interface IeventFileService {
	 public eventFile addFile(MultipartFile file, Long id, User U);
	
	 public void removeFile(Long f, Long id, User U) ;

	 public List<eventFile>findAll();
	 
	 public List<eventFile>GeteventFiles(Long id);
}
