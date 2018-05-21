package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Faculty;
import webdev.repositories.FacultyRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FacultyService {

    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyService (FacultyRepository facultyRepository){
        this.facultyRepository = facultyRepository;
    }

    @GetMapping("/api/faculty")
    public List<Faculty> findAllFaculties(){

        return facultyRepository.findAll();
    }


    @PostMapping("/api/faculty")
    public Faculty createFaculty(@RequestBody Faculty faculty){

        Faculty newFaculty = facultyRepository.save(faculty);
        return newFaculty;
    }
}
