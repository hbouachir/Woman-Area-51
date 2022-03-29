package tn.esprit.spring.womanarea51.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.womanarea51.entities.Class;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.ClassService;

@RestController
public class ClassController {
    @Autowired
    ClassService cls;
    @Autowired
    UserRepository userRepository;

    @PutMapping("/Course/{idCourse}/joinCourse")
    @PreAuthorize("hasRole('USER')")
    public Class joinCourse(@PathVariable long idCourse, Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return cls.joinCourse(idCourse, U.getId());
    }
    @DeleteMapping("/Course/{idCourse}/leaveCourse")
    @PreAuthorize("hasRole('USER')")
    public Class leaveCourse(@PathVariable long idCourse, Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return cls.leaveCourse(idCourse, U.getId());
    }
    @PostMapping("/Course/{idCourse}/setRating")
    @PreAuthorize("hasRole('USER')")
    public Class setRating(@PathVariable long idCourse, @RequestBody int rating, Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return cls.setRating(idCourse, U.getId(), rating);
    }
    @GetMapping("/Course/{idCourse}/courseRating")
    public int courseRating(@PathVariable long idCourse){
        return cls.courseRating(idCourse);

    }

}
