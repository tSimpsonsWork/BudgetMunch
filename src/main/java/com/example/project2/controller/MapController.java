package com.example.project2.controller;

import com.example.project2.entity.Results;
import com.example.project2.entity.Student;
import com.example.project2.entity.repository.StudentRepository;
import com.example.project2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="api/v1/budget")
@CrossOrigin("http://localhost:3000")
public class MapController {

    private final StudentService studentService;
    private static final Object API_KEY = "AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70";
    //private final JsonParser jsonParser;

    @Autowired
    private StudentRepository studentRepository;

//    @Autowired
//    public MapController(StudentService budget2Service, JsonParser jsonParser) {
//        this.studentService = budget2Service;
//        this.jsonParser = jsonParser;
//    }
    @Autowired
    public MapController(StudentService studentService, StudentRepository studentRepository){
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Student> newStudent(@RequestBody Student newStudent) {
        Student savedStudent = studentRepository.save(newStudent);
        return ResponseEntity.ok(savedStudent);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Student student) {
        String username = student.getUserName();
        String password = student.getPassword();

        // Perform validation
        Optional<Student> foundStudent = studentRepository.findByUserNameAndPassword(username, password);
        if (foundStudent.isPresent()) {
            // Successful login logic (return a token, etc.)
            return ResponseEntity.ok("Login successful!"); // Adjust this response as needed
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    @GetMapping("/getLocation")
    public List<Results> getGeoDetails() {
        // Retrieve the list of location details from the service
        return (List<Results>) studentService.getGeoDetails();
    }

    //register new user




}

