package tn.esprit.spring.womanarea51.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.userFile;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IUserService;
import tn.esprit.spring.womanarea51.services.IuserFileService;

@RequestMapping("/api")
@RestController
public class userFileController {
	
	@Autowired
	IUserService IUS;
	
	@Autowired
	UserRepository UR;
	
	@Autowired
	IuserFileService IUFS;
	
	
	@PostMapping(path="/user/{id}/addFile")

	    public userFile addFile(@PathVariable("id")long id, @RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {
	        User U =IUS.findOne(id);
	        return IUFS.addFile(file, U);
	    };

//	    @DeleteMapping("/user/{id}/deleteFile/{File}")
//	    @PreAuthorize("hasRole('ADMIN')")
//	    public void deleteFile(@PathVariable("File")Long File, @PathVariable("id") long id ,Authentication authentication) throws IOException {
//	        User U = IUS.findOne(id);
//	        IUFS.removeFile(File, U);};
	        
	   @GetMapping("/user/{id}/getFiles")
		 public List<userFile>eventFiles(@PathVariable("id")Long id){
			 return IUFS.findAll();
		 }

}
