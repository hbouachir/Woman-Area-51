package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.CourseCategory;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.category IN :categories")
    List<Course> findLocationsByNCategories(@Param("categories") List<CourseCategory> categories);

    @Query("select c.category from Course c group by c.category")
    List<CourseCategory> findAvailableCourseCategories();
}
