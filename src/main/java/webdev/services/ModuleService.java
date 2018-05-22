package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Course;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.CourseRepository;
import webdev.repositories.ModuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository, CourseRepository courseRepository) {
        this.moduleRepository = moduleRepository;
        this.courseRepository = courseRepository;
    }


    @GetMapping("/api/module")
    public List<Module> findAllModules() {
        return moduleRepository.findAll();
    }

    @GetMapping("/api/module/{moduleId}")
    public Module findModuleById(@PathVariable("moduleId") int moduleId) {
        return moduleRepository.findById(moduleId).orElse(null);
    }

    @GetMapping("/api/course/{courseId}/module")
    public List<Module> findAllModulesForCourse(@PathVariable("courseId") int courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            Course currentCourse = course.get();
            return currentCourse.getModules();
        }
        return null;
    }

    @PostMapping("/api/course/{courseId}/module")
    public Module createModule(@PathVariable("courseId") int courseId, @RequestBody Module newModule) {
        Optional<Course> data = courseRepository.findById(courseId);

        if (data.isPresent()) {
            Course course = data.get();
            newModule.setCourse(course);
            return moduleRepository.save(newModule);
        }
        return null;
    }

    @DeleteMapping("/api/module/{moduleId}")
    public void deleteModule(@PathVariable("moduleId") int moduleId) {
        Module module = moduleRepository.findById(moduleId).orElse(null);

        if (module != null) {
            moduleRepository.deleteById(moduleId);
        }
    }

    @PutMapping("/api/module/{moduleId}")
    public Module updateModule(@PathVariable("moduleId") int moduleId, @RequestBody Module newModule) {
        Module existingModule = moduleRepository.findById(moduleId).orElse(null);

        if (existingModule != null) {

            String title = newModule.getTitle();
            Course course = newModule.getCourse();
            List<Lesson> lessons = newModule.getLessons();

            if (title.length() > 0) {
                existingModule.setTitle(title);
            }
            if (course != null) {
                existingModule.setCourse(course);
            }

            existingModule.setLessons(lessons);
            return moduleRepository.save(existingModule);
        }

        return null;
    }
}
