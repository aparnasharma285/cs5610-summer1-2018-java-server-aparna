package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webdev.models.Widget;

public interface WidgetRepository extends JpaRepository<Widget,Integer> {

}
