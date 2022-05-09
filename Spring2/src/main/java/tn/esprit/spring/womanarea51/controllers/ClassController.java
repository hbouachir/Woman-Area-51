package tn.esprit.spring.womanarea51.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.womanarea51.entities.Class;
import tn.esprit.spring.womanarea51.entities.Course;
import tn.esprit.spring.womanarea51.entities.CourseView;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.ClassService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassController {
    @Autowired
    ClassService cls;
    @Autowired
    UserRepository userRepository;

    @PutMapping("/Course/{idCourse}/joinCourse")
    @JsonView(CourseView.Less.class)
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('USER')")
    public Class joinCourse(@PathVariable long idCourse, Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return cls.joinCourse(idCourse, U.getId());
    }
    @DeleteMapping("/Course/{idCourse}/leaveCourse")
    @JsonView(CourseView.Less.class)
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('USER')")
    public Class leaveCourse(@PathVariable long idCourse, Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return cls.leaveCourse(idCourse, U.getId());
    }
    @PostMapping("/Course/{idCourse}/setRating")
    @JsonView(CourseView.Less.class)
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('USER')")
    public Class setRating(@PathVariable long idCourse, @RequestBody int rating, Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return cls.setRating(idCourse, U.getId(), rating);
    }
    @GetMapping("/Course/{idCourse}/courseRating")
    @JsonView(CourseView.Less.class)
    public int courseRating(@PathVariable long idCourse){
        return cls.courseRating(idCourse);

    }
    @GetMapping("/Course/joined")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN') or hasRole('USER')")
    @JsonView(CourseView.Less.class)
    public List<Class> joinedCourse(Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        System.out.println(cls.joinedCourses(U));
        return cls.joinedCourses(U);
    }

}
