package tn.esprit.spring.womanarea51.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.Pub;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.PubRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;
import tn.esprit.spring.womanarea51.services.IPubService;



@RestController
public class PubController {
@Autowired
IPubService pubService;
@Autowired
PubRepository pubRepository;
@Autowired
UserRepository userRepository;
@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/addpub")
public Pub createNewUser( @RequestBody Pub p, Authentication authentication) { 
String msg="";
UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
User U = userRepository.findByUsername(U1.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));

msg="post ajouté avec ";
return pubService.add(p, U.getId());
}
///ok
@PostMapping("/uploadimage/{idPub}")
  public Pub uploadFile(@RequestParam("file") MultipartFile uploadFile,@PathVariable("idPub")Long idPub) {
    String message = "";
   try {
        //service.save(uploadFile);
    Pub p=	pubService.addPubImage(uploadFile,idPub);
    //  message = "Uploaded the file successfully: " + uploadFile.getOriginalFilename();
      message= uploadFile.getOriginalFilename();
      
    
    } catch (Exception e) {
      message = "Could not upload the file: " + uploadFile.getOriginalFilename() + "!";
     // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
    return pubService.addPubImage(uploadFile,idPub);
  }
 
}
