package tn.esprit.spring.womanarea51.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.userFile;

public interface IuserFileService {
	
	public userFile addFile(MultipartFile file,User U);
	 
	// public void removeFile(Long f, User U) ;
	 
	 public List<userFile>findAll();
	 
	 public List<userFile>GetUserFiles(Long id);
	 

}
