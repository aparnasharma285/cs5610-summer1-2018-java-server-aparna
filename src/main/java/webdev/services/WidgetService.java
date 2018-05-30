package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.ListType;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

    private final WidgetRepository widgetRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public WidgetService(WidgetRepository widgetRepository, TopicRepository topicRepository) {
        this.widgetRepository = widgetRepository;
        this.topicRepository = topicRepository;
    }


    @GetMapping("/api/widget")
    public List<Widget> findAllWidget() {

        return widgetRepository.findAll();
    }

    @GetMapping("/api/widget/{widgetId}")
    public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
        return widgetRepository.findById(widgetId).orElse(null);
    }

    @GetMapping("/api/topic/{topicId}/widget")
    public List<Widget> findWidgetByTopic(@PathVariable("topicId") int topicId) {

        Topic topic = topicRepository.findById(topicId).orElse(null);

        if (topic != null) {

            return widgetRepository.findSortedWidgetsForTopic(topic);
        }

        return null;
    }

    @PostMapping("/api/topic/{topicId}/widget")
    public Widget createWidget(@PathVariable("topicId") int topicId, @RequestBody Widget widget) {

        Topic existingTopic = topicRepository.findById(topicId).orElse(null);

        if (existingTopic != null) {

            widget.setTopic(existingTopic);
            return widgetRepository.save(widget);
        }

        return null;
    }

    @PostMapping("api/widget/save/{topicId}")
    public List<Widget> saveAllWidgets(@PathVariable("topicId") int topicId,@RequestBody List<Widget> widgets){
        List<Widget> newWidgetList = new ArrayList<Widget>();
        Topic topic = topicRepository.findById(topicId).orElse(null);

        if(topic != null) {
            for(Widget w: topic.getWidgets()){
                widgetRepository.delete(w);
            }

            int orderCount = 1;
            for (Widget widget : widgets) {
                widget.setTopic(topic);
                widget.setOrder(orderCount);
                orderCount++;
                newWidgetList.add(widgetRepository.save(widget));
            }
        }
        return newWidgetList;
    }

    @PutMapping("/api/widget/{widgetId}")
    public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget newWidget) {

        Widget widget = widgetRepository.findById(widgetId).orElse(null);

        if (widget != null) {

            String name = newWidget.getName();
            String text = newWidget.getText();
            String className = newWidget.getClassName();
            int order = newWidget.getOrder();
            String style = newWidget.getStyle();
            String width = newWidget.getWidth();
            String height = newWidget.getHeight();
            String type = newWidget.getWidgetType();
            int size = newWidget.getSize();
            String href = newWidget.getHref();
            String src = newWidget.getSrc();
            String listItems = newWidget.getListItems();
            ListType listType = newWidget.getListType();

            if (name != null) {
                widget.setName(name);
            }
            if (text != null) {
                widget.setText(text);
            }
            if (className != null) {
                widget.setClassName(className);
            }
            if (order > 0) {
                widget.setOrder(order);
            }
            if (style != null) {
                widget.setStyle(style);
            }
            if (width != null) {
                widget.setWidth(width);
            }
            if (height != null) {
                widget.setHeight(height);
            }
            if (type != null) {

                if (type.equals("Heading")) {

                    if (size > 0) {
                        widget.setSize(size);
                    }
                    widget.setHref(null);
                    widget.setSrc(null);
                    widget.setListItems(null);
                    widget.setListType(null);
                }

                if (type.equals("Link")) {
                    if (href != null) {
                        widget.setHref(href);
                    }
                    widget.setSize(0);
                    widget.setSrc(null);
                    widget.setListItems(null);
                    widget.setListType(null);
                }

                if (type.equals("Image")) {
                    if (src != null) {
                        widget.setSrc(src);

                    }
                    widget.setSize(0);
                    widget.setHref(null);
                    widget.setListItems(null);
                    widget.setListType(null);
                }

                if (type.equals("Paragraph")) {
                    widget.setSize(0);
                    widget.setHref(null);
                    widget.setSrc(null);
                    widget.setListItems(null);
                    widget.setListType(null);
                }

                if (type.equals("List")) {
                    if (listItems != null) {
                        widget.setListItems(listItems);
                    }
                    if (listType != null) {
                        widget.setListType(listType);
                    }
                    widget.setSize(0);
                    widget.setHref(null);
                    widget.setSrc(null);
                }
            }


            return widgetRepository.save(widget);
        }

        return null;

    }

    @DeleteMapping("/api/widget/{widgetId}")
    public void deleteWidget(@PathVariable("widgetId") int widgetId) {
        Widget widget = widgetRepository.findById(widgetId).orElse(null);

        if (widget != null) {
            widgetRepository.delete(widget);
        }
    }

}
