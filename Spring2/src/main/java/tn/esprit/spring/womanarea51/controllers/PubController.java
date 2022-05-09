package tn.esprit.spring.womanarea51.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.Post;
import tn.esprit.spring.womanarea51.entities.Pub;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.PubRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.security.services.UserDetailsImpl;
import tn.esprit.spring.womanarea51.services.IPubService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PubController {
@Autowired
IPubService pubService;
@Autowired
PubRepository pubRepository;
@Autowired
UserRepository userRepository;
@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/addpub")
public Pub createNewPub( @RequestBody Pub p, Authentication authentication) { 
String msg="";
UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
User U = userRepository.findByUsername(U1.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));

msg="post ajouté avec ";
return pubService.add(p, U.getId());
}
////modifier li5raniyin
@RequestMapping(value = "/getpub/{id}")
public Pub getPub(@PathVariable Long id) {

Pub p= pubService.getPub(id);
			return p;
}
@RequestMapping(value = "/pubupp", method = RequestMethod.PUT)

public void updatePub(@RequestBody Pub p) {
	
	 pubService.upPub(p);
}
@RequestMapping(value = "/pub/{id}", method = RequestMethod.DELETE)
public void deletePub(@PathVariable Long id) {
	 pubService.deletePub(id);
	 	 
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

@GetMapping("/getpubs")
public List<Pub> getPub() { 


return pubService.getAllPub();
}
@GetMapping("/pubfb/{token}/{id}")
public void listPubFb(@PathVariable String token,@PathVariable Long id){
	pubService.partageFb(token, id);
	System.out.println("dooonnnee taw fb");
	 
}
@PostMapping("/pubfbtest/{token}/{id}")
public void uploadFile(@RequestParam("file") MultipartFile uploadFile,@PathVariable("id")Long id,@PathVariable("token") String token) {
  String message = "";
 try {
      //service.save(uploadFile);
	 pubService.AddPub(id, uploadFile, token);
 // Pub p=	pubService.addPubImage(uploadFile,idPub);
  //  message = "Uploaded the file successfully: " + uploadFile.getOriginalFilename();
    message= uploadFile.getOriginalFilename();
    
  
  } catch (Exception e) {
    message = "Could not upload the file: " + uploadFile.getOriginalFilename() + "!";
   // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
  }
//  return pubService.addPubImage(uploadFile,idPub);
}
}
