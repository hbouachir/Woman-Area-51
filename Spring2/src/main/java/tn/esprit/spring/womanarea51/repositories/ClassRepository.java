package tn.esprit.spring.womanarea51.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.womanarea51.entities.Class;
import tn.esprit.spring.womanarea51.entities.ClassKey;
import tn.esprit.spring.womanarea51.entities.Course;

import java.util.List;

@Repository
public interface ClassRepository extends CrudRepository<Class, ClassKey> {
    @Query("SELECT AVG(c.rating) FROM Class c where c.course.courseId = :courseId and c.rating <> -1")
    int courseRating(@Param("courseId") Long courseId);
    @Query("SELECT c FROM Class c where c.student.id = :studentId")
    List<Class> joinedClasses(@Param("studentId") Long studentId);
}
