package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.Class;
import tn.esprit.spring.womanarea51.entities.Course;
import tn.esprit.spring.womanarea51.entities.User;

import java.util.List;

public interface ClassService {
    Class joinCourse(Long idCourse, Long idUser);
    Class leaveCourse(Long idCourse, Long idUser);
    Class setRating(Long idCourse, Long idUser, int rating);
    int courseRating(Long idCourse);
    List<Class> joinedCourses(User u);

}
