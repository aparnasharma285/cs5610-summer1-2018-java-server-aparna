package com.webdev.summer.services;

import com.webdev.summer.models.Person;
import com.webdev.summer.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){

        this.personRepository = personRepository;
    }

    @GetMapping("/api/users")
    public List<Person> findAllUsers(){
        return personRepository.findAll();
    }

}
