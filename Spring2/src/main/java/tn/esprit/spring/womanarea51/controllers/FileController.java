package tn.esprit.spring.womanarea51.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.womanarea51.entities.File;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.FileService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    FileService fs;
    @Autowired
    UserRepository userRepository;

    @PostMapping(path="/Course/{idCourse}/addFile")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public File addFile(@PathVariable long idCourse, @RequestParam("file") MultipartFile file, Authentication authentication) {
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return fs.addFile(file, idCourse,U);
    };

    @DeleteMapping("/Course/{idCourse}/deleteFile/{idFile}")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public void deleteFile(@PathVariable long idFile,@PathVariable long idCourse ,Authentication authentication) {
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        fs.removeFile(idFile, idCourse, U);};

    /*@GetMapping("/getFiles/")
    public List<File> findFiles(){return fs.findAll();}*/

}
