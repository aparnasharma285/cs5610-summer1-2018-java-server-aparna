package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import webdev.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacultyRepository extends JpaRepository<Faculty,Integer> {



}
