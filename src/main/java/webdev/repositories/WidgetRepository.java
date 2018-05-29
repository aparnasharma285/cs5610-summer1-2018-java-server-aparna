package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webdev.models.Topic;
import webdev.models.User;
import webdev.models.Widget;

import java.util.Optional;

public interface WidgetRepository extends JpaRepository<Widget,Integer> {

}
