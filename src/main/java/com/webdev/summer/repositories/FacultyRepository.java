package com.webdev.summer.repositories;

import com.webdev.summer.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacultyRepository extends JpaRepository<Faculty,Long> {
}
