package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.Course;
import tn.esprit.spring.womanarea51.entities.CourseCategory;

import java.util.List;

public interface CourseService {
    Course add_course(Course c);
    Course update_course(Course c);
    void delete_course(Course c);
    List<Course> findAll_courses();
    List<Course> findByCat_courses(List<CourseCategory> category);
    List<CourseCategory> availableCourseCategories();
}
