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

import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.entities.eventFile;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IUserService;
import tn.esprit.spring.womanarea51.services.IeventFileService;

@RestController
public class eventFileController {
	
	@Autowired
	IUserService IUS;
	
	@Autowired
	IeventFileService IEFS;
	
	@Autowired
	UserRepository UR;
	
	@PostMapping(path="/event/{id}/addFile")
  //  @PreAuthorize("hasRole('ADMIN')")
    public eventFile addFile(@PathVariable("id")long id, @RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {
        User U = UR.findByUsername(authentication.getName()).orElse(null);
        return IEFS.addFile(file, id, U);
    };

    @DeleteMapping("/event/{id}/deleteFile/{File}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteFile(@PathVariable("File")Long File, @PathVariable("id") long id ,Authentication authentication) throws IOException {
        User U = UR.findByUsername(authentication.getName()).orElse(null);
        IEFS.removeFile(File, id, U);};
        
   @GetMapping("/event/{id}/getFiles")
	 public List<eventFile>eventFiles(@PathVariable("id")Long id){
		 return IEFS.GeteventFiles(id);
	 }

        
   

}
