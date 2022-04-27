package tn.esprit.spring.womanarea51.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.FundFiles;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IFundFilesService;
import tn.esprit.spring.womanarea51.services.IUserService;

@RestController
public class FundFileController {
	
	@Autowired
	IUserService IUS;
	
	@Autowired
	IFundFilesService IFFS;
	
	@Autowired
	UserRepository UR;
	
	@PostMapping(path="/fund/{id}/addFile")
	  
	    public FundFiles addFile(@PathVariable("id")long id, @RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {
	        User U = UR.findByUsername(authentication.getName()).orElse(null);
	        return IFFS.addFile(file, id, U);
	    };

	    @DeleteMapping("/fund/{id}/deleteFile/{File}")

	    public void deleteFile(@PathVariable("File")Long File, @PathVariable("id") long id ,Authentication authentication) throws IOException {
	        User U = UR.findByUsername(authentication.getName()).orElse(null);
	        IFFS.removeFile(File, id, U);};
	        
	   @GetMapping("/fund/{id}/getFiles")
		 public List<FundFiles>eventFiles(@PathVariable("id")Long id){
			 return IFFS.GeteventFiles(id);
		 }

	        
	   

}
