package tn.esprit.spring.womanarea51.controllers;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.FundCategoryFile;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.IFundCatFileService;
import tn.esprit.spring.womanarea51.services.IUserService;

@RestController
public class fundCategoryFileController {
	
	@Autowired
	IUserService IUS;
	
	@Autowired
	IFundCatFileService IFSS;
	
	@Autowired
	UserRepository UR;
	
	
	
	@PostMapping("/fundCategory/addFile/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public FundCategoryFile addFile(@PathVariable("id") long id, @RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {
		User U=UR.findByUsername(authentication.getName()).orElse(null);
        return IFSS.addFile(file, id, U);
    };
    

//    @DeleteMapping("/fundCategory/{id}/deleteFile/{File}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public void deleteFile(@PathVariable("File")Long File, @PathVariable("id") long id ,Authentication authentication) throws IOException {
//    	User U=UR.findByUsername(authentication.getName()).orElse(null);
//        IFSS.removeFile(File, id, U);};
//        
        
    @GetMapping("fundCategory/getFiles/{id}")
    public FundCategoryFile GetFundFile (@PathVariable("id") Long id){
    	return IFSS.GetFundFiles(id);
    }
   
    
}
