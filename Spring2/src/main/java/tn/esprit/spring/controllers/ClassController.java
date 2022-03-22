package tn.esprit.spring.controllers;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.entities.Class;
import tn.esprit.spring.services.ClassService;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class ClassController {
    @Autowired
    ClassService cls;

    @PostMapping("/joinCourse/{idCourse}")
    public Class joinCourse(@PathVariable long idCourse){
        return cls.joinCourse(idCourse, 1L);
    }
    @PostMapping("/leaveCourse/{idCourse}")
    public Class leaveCourse(@PathVariable long idCourse){
        return cls.leaveCourse(idCourse, 1L);
    }
    @PostMapping("/setRating/{idCourse}")
    public Class setRating(@PathVariable long idCourse, @RequestBody int rating){
        return cls.setRating(idCourse, 1L, rating);
    }
    @GetMapping("/courseRating/{idCourse}")
    public int courseRating(@PathVariable long idCourse){
        return cls.courseRating(idCourse);

    }

}
