package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import webdev.models.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {

    @Query("SELECT u FROM Course u ORDER BY u.title")
     public List<Course> sortList();
}
