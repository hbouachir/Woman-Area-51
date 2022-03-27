package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.Course;
import tn.esprit.spring.womanarea51.entities.CourseCategory;
import tn.esprit.spring.womanarea51.entities.User;

import java.util.List;

public interface CourseService {
    Course add_course(Course c, User u);
    Course update_course(Course c, User u);
    Course findCourse(Long idCourse);
    void delete_course(Course c, User u);
    List<Course> findAll_courses();
    List<Course> findByCat_courses(List<CourseCategory> category);
    List<CourseCategory> availableCourseCategories();
    List<Course> findInstructorAll_courses(String instructorUsername);
}
