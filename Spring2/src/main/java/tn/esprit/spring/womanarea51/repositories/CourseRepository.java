package tn.esprit.spring.womanarea51.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.womanarea51.entities.Course;
import tn.esprit.spring.womanarea51.entities.CourseCategory;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.category IN :categories")
    List<Course> findLocationsByNCategories(@Param("categories") List<CourseCategory> categories);

    @Query("select c.category from Course c group by c.category")
    List<CourseCategory> findAvailableCourseCategories();

    @Query("SELECT c FROM Course c WHERE c.instructor.username = :instructorUsername")
    List<Course> findInstructorAll_courses(@Param("instructorUsername") String instructorUsername);
}
