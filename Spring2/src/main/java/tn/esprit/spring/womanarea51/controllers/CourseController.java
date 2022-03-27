package tn.esprit.spring.womanarea51.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.womanarea51.entities.Course;
import tn.esprit.spring.womanarea51.entities.CourseCategory;
import tn.esprit.spring.womanarea51.entities.CourseView;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.repositories.UserRepository;
import tn.esprit.spring.womanarea51.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/Course")
public class CourseController {


    @Autowired
    CourseService cs ;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/addCourse")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @JsonView(CourseView.Less.class)
    public Course addCourse(@RequestBody Course c, Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return cs.add_course(c, U);
    }

    @PostMapping("/updateCourse")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public Course updateCourse(@RequestBody Course c, Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        return cs.update_course(c,U);
    }

    @DeleteMapping("/deleteCourse")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public void deleteCourse(@RequestBody Course c, Authentication authentication){
        User U = userRepository.findByUsername(authentication.getName()).orElse(null);
        cs.delete_course(c,U);
    }

    @GetMapping("/getAllCourses")
    @JsonView(CourseView.Less.class)
    public List<Course> getAllCourses(){
        return cs.findAll_courses();
    }

    @GetMapping("/getCourse/{idCourse}")
    @JsonView(CourseView.More.class)
    public Course getCourse(@PathVariable Long idCourse){
        return cs.findCourse(idCourse);
    }

    @GetMapping("/getAllCourses/{username}")
    @JsonView(CourseView.Less.class)
    public List<Course> getAllInstructorCourses(@PathVariable String username){
        return cs.findInstructorAll_courses(username);
    }

    @GetMapping("/getCoursesByNCat")
    @JsonView(CourseView.Less.class)
    public List<Course> getCoursesByNCat(@RequestBody List<CourseCategory> categories){
        return cs.findByCat_courses(categories);
    }
    @GetMapping("/getAvailableCoursesCat")
    public List<CourseCategory> getAvailableCoursesCat(){
        return cs.availableCourseCategories();
    }

}
