package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webdev.models.Student;
import webdev.repositories.StudentRepository;

import java.util.List;

@RestController
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService (StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping("/api/student")
    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    @PostMapping("/api/student")
    public Student createStudent(@RequestBody Student student){

        Student newStudent = studentRepository.save(student);
        return newStudent;
    }
}
