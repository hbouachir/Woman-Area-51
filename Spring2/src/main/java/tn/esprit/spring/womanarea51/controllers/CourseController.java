package tn.esprit.spring.womanarea51.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.womanarea51.entities.Course;
import tn.esprit.spring.womanarea51.entities.CourseCategory;
import tn.esprit.spring.womanarea51.services.CourseService;

import java.util.List;

@RestController
public class CourseController {


    @Autowired
    CourseService cs ;


    @PostMapping("/addCourse")
    public Course addCourse(@RequestBody Course c){
        return cs.add_course(c);
    }

    @PostMapping("/updateCourse")
    public Course updateCourse(@RequestBody Course c){
        return cs.update_course(c);
    }

    @DeleteMapping("/deleteCourse")
    public void deleteCourse(@RequestBody Course c){
        cs.delete_course(c);
    }
    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses(){
        return cs.findAll_courses();
    }

    @GetMapping("/getCoursesByNCat")
    public List<Course> getCoursesByNCat(@RequestBody List<CourseCategory> categories){
        return cs.findByCat_courses(categories);
    }
    @GetMapping("/getAvailableCoursesCat")
    public List<CourseCategory> getAvailableCoursesCat(){
        return cs.availableCourseCategories();
    }

}
