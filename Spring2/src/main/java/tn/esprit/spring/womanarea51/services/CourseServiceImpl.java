package tn.esprit.spring.womanarea51.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.womanarea51.entities.Course;
import tn.esprit.spring.womanarea51.entities.CourseCategory;
import tn.esprit.spring.womanarea51.repositories.CourseRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseRepository cr;

    @Autowired
    UserRepository ur;

    @Override
    public Course add_course(Course c) {
        return cr.save(c);
    }

    @Override
    public Course update_course(Course c) {
        return cr.save(c);
    }

    @Override
    public void delete_course(Course c) {
        cr.delete(c);
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
}
