package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webdev.models.Topic;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
}
