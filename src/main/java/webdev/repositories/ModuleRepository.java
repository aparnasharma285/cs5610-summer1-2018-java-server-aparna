package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webdev.models.Module;

public interface ModuleRepository extends JpaRepository<Module,Integer> {

}
