package tn.esprit.spring.womanarea51.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.womanarea51.entities.File;
import tn.esprit.spring.womanarea51.entities.Filepost;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.FilepostService;
@RestController
public class FilepostController {
    @Autowired
    FilepostService fs;
    @Autowired
    UserRepository userRepository;

    @PostMapping(path="/Post/{idPost}/addFile")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Filepost addFile(@PathVariable long idPost, @RequestParam("file") MultipartFile file, Authentication authentication) {
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        System.out.println("Error 34");
        return fs.addFile(file, idPost,U);
    };

    @DeleteMapping("/Post/{idPost}/deleteFile/{idFile}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteFile(@PathVariable long idFile,@PathVariable long idPost ,Authentication authentication) {
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        fs.removeFile(idFile, idPost, U);};
}
