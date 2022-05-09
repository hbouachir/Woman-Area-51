package tn.esprit.spring.womanarea51.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.womanarea51.entities.*;
import tn.esprit.spring.womanarea51.repositories.CourseRepository;
import tn.esprit.spring.womanarea51.repositories.RoleRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseRepository cr;

    @Autowired
    UserRepository ur;

    @Autowired
    RoleRepository rr;

    @Override
    public Course add_course(Course c, User U) {
        if (U.getRoles().contains(rr.findByName(ERole.ROLE_ADMIN).orElse(null))) c.setInstructor(ur.findByUsername(c.getInstructor().getUsername()).orElse(U));
        else c.setInstructor(U);
        return cr.save(c);
    }

    @Override
    public Course update_course(Course c, User U) {
        Course old_C = cr.findById(c.getCourseId()).orElse(null);
        if (old_C == null) return null;
        if (U.getUsername().equals(old_C.getInstructor().getUsername()) || U.getRoles().contains(rr.findByName(ERole.ROLE_ADMIN).orElse(null))) {
            c.setQuiz(old_C.getQuiz());
            c.setFiles(old_C.getFiles());
            c.setInstructor(old_C.getInstructor());
            return cr.save(c);
        }
            return null;
        }

    @Override
    public Course findCourse(Long idCourse) {
        return cr.findById(idCourse).orElse(null);
    }

    @Override
    public void delete_course(Long idCourse, User u) {
        Course old_course = cr.findById(idCourse).orElse(null);
        if (old_course != null) {
            if (u.getRoles().contains(rr.findByName(ERole.ROLE_ADMIN).orElse(null))) cr.delete(old_course);
            else {
                if (old_course.getInstructor().getUsername().equals(u.getUsername())){
                    cr.delete(old_course);
                }
            }
        }

    }

    @Override
    public List<Course> findAll_courses() {
        return (List<Course>) cr.findAll();
    }

    @Override
    public List<Course> findByCat_courses(List<CourseCategory> category) {
        return cr.findLocationsByNCategories(category);
    }

    @Override
    public List<CourseCategory> availableCourseCategories() {
        return cr.findAvailableCourseCategories();
    }

    @Override
    public List<Course> findInstructorAll_courses(String instructorUsername) {
        return cr.findInstructorAll_courses(instructorUsername);
    }
}
