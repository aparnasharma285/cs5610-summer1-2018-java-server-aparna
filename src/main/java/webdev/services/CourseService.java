package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Course;
import webdev.repositories.CourseRepository;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {

        this.courseRepository = courseRepository;
    }

    @GetMapping("/api/course")
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/api/course/{courseId}")
    public Course findCourseById(@PathVariable("courseId") int courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @PostMapping("/api/course")
    public Course createCourse(@RequestBody Course course) {

        Course newCourse = courseRepository.save(course);
        return newCourse;
    }

    @DeleteMapping("/api/course/{courseId}")
    public void deleteCourse(@PathVariable("courseId") int courseId) {
        Course existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse != null) {
            courseRepository.deleteById(courseId);
        }
    }

    @PutMapping("/api/course")
    public Course updateCourse(@RequestBody Course course) {

        Course existingCourse = courseRepository.findById(course.getId()).orElse(null);

        if (existingCourse != null) {

            String title = course.getTitle();
            Date created = course.getCreated();
            Date modified = course.getModified();

            if (title.length() > 0 && !(existingCourse.getTitle().equals(title))) {
                existingCourse.setTitle(title);
            }

            if (created != null) {
                existingCourse.setCreated(created);
            }


            existingCourse.setModified(modified);
            return courseRepository.save(existingCourse);
        }

        return existingCourse;
    }

    @GetMapping("/api/course/sort")
    public List<Course> sortList(){
        return courseRepository.sortList();
    }

}
