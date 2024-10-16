package com.example.project2.controller;

import com.example.project2.entity.Response;
import com.example.project2.entity.Result;
import com.example.project2.entity.Student;
import com.example.project2.entity.repository.StudentRepository;
import com.example.project2.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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
    public MapController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> newStudent(@RequestBody Student newStudent) {
        // Check if the username already exists
        if (studentService.existsByUsername(newStudent.getUserName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
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

    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
        boolean exists = studentService.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }


    @GetMapping("/getLocation")
    public List<Map<String, Object>> getGeoDetails() throws JsonProcessingException {
        Response responseBody = studentService.getGeoDetails();
        List<Result> resultList = List.of(responseBody.getResult());

        // Create a new list to store the mapped results
        List<Map<String, Object>> mappedResults = new ArrayList<>();

        // Loop through each result and map the price level to a string range
        resultList.forEach(post -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("name", post.getName());
            resultMap.put("rating", post.getRating());
            resultMap.put("vicinity", post.getVicinity());

            // Map price level to string range
            String priceLevelString;
            switch (post.getPrice_level()) {
                case 0:
                    priceLevelString = "$1-$10";
                    break;
                case 1:
                    priceLevelString = "$10-$25";
                    break;
                case 2:
                    priceLevelString = "$25-$45";
                    break;
                default:
                    priceLevelString = "$50+";
                    break;
            }
            resultMap.put("price_level", priceLevelString); // Add the string range to the result map

            mappedResults.add(resultMap); // Add the modified result to the list
        });

        return mappedResults; // Return the modified list
    }
}

//register new user
    //inside controller class, do responsebody.getResults, store it as a list.of, iterate the list


