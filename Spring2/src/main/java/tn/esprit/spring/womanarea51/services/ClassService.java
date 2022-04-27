package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.Class;

public interface ClassService {
    Class joinCourse(Long idCourse, Long idUser);
    Class leaveCourse(Long idCourse, Long idUser);
    Class setRating(Long idCourse, Long idUser, int rating);
    int courseRating(Long idCourse);
}
