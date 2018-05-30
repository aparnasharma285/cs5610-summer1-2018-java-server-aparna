package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import webdev.models.Topic;
import webdev.models.User;
import webdev.models.Widget;

import java.util.List;
import java.util.Optional;

public interface WidgetRepository extends JpaRepository<Widget,Integer> {

    @Query("select w from Widget w where w.topic=:topic order by w.order")
    public List<Widget> findSortedWidgetsForTopic(@Param("topic") Topic topic);
}
