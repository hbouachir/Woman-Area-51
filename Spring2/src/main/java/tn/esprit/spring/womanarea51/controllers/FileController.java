package tn.esprit.spring.womanarea51.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.womanarea51.entities.File;
import tn.esprit.spring.womanarea51.services.FileService;

import java.util.List;

@RestController
public class FileController {

    @Autowired
    FileService fs;

    @PostMapping("/addFile/{idCourse}")
    public File addFile(@PathVariable long idCourse, @RequestParam("file") MultipartFile file) {return fs.addFile(file, idCourse);};

    @DeleteMapping("/deleteFile/{idFile}")
    public void deleteFile(@PathVariable long idFile) {fs.removeFile(idFile);};

    @GetMapping("/getFiles/")
    public List<File> findFiles(){return fs.findAll();}

}
