package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.LessonRepository;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

    private final WidgetRepository widgetRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public WidgetService(WidgetRepository widgetRepository,TopicRepository topicRepository) {
        this.widgetRepository = widgetRepository;
        this.topicRepository = topicRepository;
    }


    @GetMapping("/api/widget")
    public List<Widget> findAllWidget() {

        return widgetRepository.findAll();
    }

    @GetMapping("/api/widget/{widgetId}")
    public Widget findWidgetById(@PathVariable("widgetId") int widgetId){
        return widgetRepository.findById(widgetId).orElse(null);
    }

    @GetMapping("/api/lesson/{topicId}/widget")
    public List<Widget> findWidgetByLesson(@PathVariable("topicId") int topicId){

        Topic topic = topicRepository.findById(topicId).orElse(null);

        if(topic !=null){

            return  topic.getWidgets();
        }

        return null;
    }

    @PostMapping("/api/lesson/{topicId}/widget")
    public Widget createWidget(@PathVariable("topicId") int topicId, @RequestBody Widget widget){

        Topic existingTopc = topicRepository.findById(topicId).orElse(null);

        if(existingTopc != null){

            widget.setTopic(existingTopc);
            return widgetRepository.save(widget);
        }

        return null;
    }

    @PutMapping("/api/widget/{widgetId}")
    public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget newWidget){

        Widget widget = widgetRepository.findById(widgetId).orElse(null);

        if(widget != null){

            String name = newWidget.getName();
            String text = newWidget.getText();
            String className = newWidget.getClassName();
            int order = newWidget.getOrder();
            String style = newWidget.getStyle();
            String width = newWidget.getWidth();
            String height = newWidget.getHeight();

            if(name != null){
                widget.setName(name);
            }
            if(text!=null){
                widget.setText(text);
            }
            if(className != null){
                widget.setClassName(className);
            }
            if(order > 0){
                widget.setOrder(order);
            }
            if(style != null){
                widget.setStyle(style);
            }
            if(width != null){
                widget.setWidth(width);
            }
            if(height != null){
                widget.setHeight(height);
            }

            return widgetRepository.save(widget);
        }

        return null;

    }

    @DeleteMapping("/api/widget/{widgetId}")
    public void deleteWidget(@PathVariable("widgetId") int widgetId){
        Widget widget = widgetRepository.findById(widgetId).orElse(null);

        if(widget != null){
            widgetRepository.delete(widget);
        }
    }
}
