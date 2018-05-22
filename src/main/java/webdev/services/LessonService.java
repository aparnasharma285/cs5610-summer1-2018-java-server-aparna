package webdev.services;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository, ModuleRepository moduleRepository) {
        this.lessonRepository = lessonRepository;
        this.moduleRepository = moduleRepository;
    }

    @PostMapping("/api/course/{cid}/module/{mid}/lesson")
    public Lesson createLesson(@PathVariable("cid") int CourseId, @PathVariable("mid") int moduleId,
                               @RequestBody Lesson newlesson) {

        Module module = moduleRepository.findById(moduleId).orElse(null);

        if (module != null) {
            newlesson.setModule(module);
            return lessonRepository.save(newlesson);
        }

        return null;

    }

    @GetMapping("/api/lesson")
    public List<Lesson> findAllLessons(){
       return lessonRepository.findAll();
    }

    @GetMapping("/api/lesson/{lid}")
    public Lesson findLessonById(@PathVariable("lid")int lessonId){
        return lessonRepository.findById(lessonId).orElse(null);
    }

    @GetMapping("/api/course/{cid}/module/{mid}/lesson")
    public List<Lesson> findAllLessonssForModule(@PathVariable("cid") int courseId, @PathVariable("mid") int moduleId){
        Module module = moduleRepository.findById(moduleId).orElse(null);
        if(module != null){
            return module.getLessons();
        }
        return null;
    }

    @DeleteMapping("/api/lesson/{lid}")
    public void deleteLesson(@PathVariable("lid") int lessonId){
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        if(lesson != null){
            lessonRepository.delete(lesson);
        }
    }

    @PutMapping("/api/lesson/{lid}")
    public Lesson updateLesson(@PathVariable("lid") int lessonId, @RequestBody Lesson newLesson){

        Lesson existingLesson = lessonRepository.findById(lessonId).orElse(null);

        if(existingLesson != null){

            String title = newLesson.getTitle();
            Module module = newLesson.getModule();

            if (title!= null){
                existingLesson.setTitle(title);
            }
            if(module!= null){
                existingLesson.setModule(module);
            }

            return lessonRepository.save(existingLesson);
        }

        return null;
    }

}
