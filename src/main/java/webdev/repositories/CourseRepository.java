package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import webdev.models.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
