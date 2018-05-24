package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.models.Topic;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;
import webdev.repositories.TopicRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicService {

    private final TopicRepository topicRepository;
    private final LessonRepository lessonRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository,LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
        this.topicRepository = topicRepository;
    }

    @PostMapping("/api/lesson/{lid}/topic")
    public Topic createTopic(@PathVariable("lid") int lessonId, @RequestBody Topic newTopic) {
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);

        if (lesson != null) {
            newTopic.setLesson(lesson);
            return topicRepository.save(newTopic);
        }

        return null;

    }


    @GetMapping("/api/lesson/{lid}/topic")
    public List<Topic> findAllTopicsForLesson(@PathVariable("lid") int lessonId){
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        if(lesson != null){
            return lesson.getTopics();
        }
        return null;
    }

    @DeleteMapping("/api/topic/{tid}")
    public void deleteLesson(@PathVariable("tid") int topicId){
        Topic topic = topicRepository.findById(topicId).orElse(null);
        if(topic != null){
            topicRepository.delete(topic);
        }
    }

}
