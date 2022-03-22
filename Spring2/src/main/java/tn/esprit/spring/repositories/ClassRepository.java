package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Class;
import tn.esprit.spring.entities.ClassKey;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.CourseCategory;

import java.util.List;

@Repository
public interface ClassRepository extends CrudRepository<Class, ClassKey> {
    @Query("SELECT AVG(c.rating) FROM Class c where c.course.courseId = :courseId and c.rating <> -1")
    int courseRating(@Param("courseId") Long courseId);
}
