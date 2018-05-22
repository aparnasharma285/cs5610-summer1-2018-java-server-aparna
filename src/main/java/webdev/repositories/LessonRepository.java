package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webdev.models.Lesson;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
}
