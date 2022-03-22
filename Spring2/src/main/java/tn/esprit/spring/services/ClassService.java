package tn.esprit.spring.services;

import tn.esprit.spring.entities.Class;

public interface ClassService {
    Class joinCourse(Long idCourse, Long idUser);
    Class leaveCourse(Long idCourse, Long idUser);
    Class setRating(Long idCourse, Long idUser, int rating);
    int courseRating(Long idCourse);
}
