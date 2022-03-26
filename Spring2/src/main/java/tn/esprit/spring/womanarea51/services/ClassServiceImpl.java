package tn.esprit.spring.womanarea51.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.womanarea51.entities.Class;
import tn.esprit.spring.womanarea51.entities.ClassKey;
import tn.esprit.spring.womanarea51.repositories.ClassRepository;
import tn.esprit.spring.womanarea51.repositories.CourseRepository;
import tn.esprit.spring.womanarea51.repositories.UserRepository;

import java.util.Date;
@Service
public class ClassServiceImpl implements ClassService{
    @Autowired
    UserRepository ur;
    @Autowired
    CourseRepository cr;
    @Autowired
    ClassRepository clr;

    @Override
    public Class joinCourse(Long idCourse, Long idUser) {
        ClassKey ck = new ClassKey(idCourse, idUser);
        Date date = new Date();
        Class c = clr.findById(ck).orElse(new Class());
        System.out.println(c);
        if (c.getId() == null) {
            c.setId(ck);
            c.setStudent(ur.findById(idUser).get());
            c.setCourse(cr.findById(idCourse).get());
            c.setDate(date);
            c.setRating(-1);
            c.setStatus("Joined");
            return clr.save(c);
        }
        return c;

    }

    @Override
    public Class leaveCourse(Long idCourse, Long idUser) {
        Class c = clr.findById(new ClassKey(idCourse,idUser)).orElse(null);
        if (c.getId() != null && c.getStatus().equals("Joined")) {
            clr.delete(c);
            return null;
        }
        return c;
    }

    @Override
    public Class setRating(Long idCourse, Long idUser, int rating) {
        Class c = clr.findById(new ClassKey(idCourse,idUser)).orElse(null);
        if (c.getId() != null){
            c.setRating(rating);
            if (c.getCourse().getStart_date().before(new Date()) && c.getRating() >= 0 && c.getRating() <= 10) {
                return clr.save(c);
            }
            return c;
        }
        return null;
    }
    @Override
    public int courseRating(Long idCourse) {
        try {
            return clr.courseRating(idCourse);
        }catch (Exception e){
            return -1;
        }

    }
}
