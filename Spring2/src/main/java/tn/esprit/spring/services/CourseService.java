package tn.esprit.spring.services;

import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.CourseCategory;

import java.util.List;

public interface CourseService {
    Course add_course(Course c);
    Course update_course(Course c);
    void delete_course(Course c);
    List<Course> findAll_courses();
    List<Course> findByCat_courses(List<CourseCategory> category);
    List<CourseCategory> availableCourseCategories();
}
