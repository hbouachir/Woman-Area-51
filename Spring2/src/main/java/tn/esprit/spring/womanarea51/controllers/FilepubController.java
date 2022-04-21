package tn.esprit.spring.womanarea51.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.Filepub;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.FilepubService;

@RestController
public class FilepubController {
	@Autowired
    FilepubService fs;
    @Autowired
    UserRepository userRepository;

    @PostMapping(path="/Pub/{idPub}/addFile")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Filepub addFile(@PathVariable long idPub, @RequestParam("file") MultipartFile file, Authentication authentication) {
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return fs.addFilepub(file,idPub,U);
    };

    @DeleteMapping("/Pub/{idPub}/deleteFile/{idFile}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteFile(@PathVariable long idFile,@PathVariable long idPub ,Authentication authentication) {
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        fs.removeFile(idFile, idPub, U);};
}
