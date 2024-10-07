package com.example.project2.controller;

import com.example.project2.entity.Response;
import com.example.project2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/budget")
@CrossOrigin("http://localhost:3000")
public class MapController {

    private final StudentService studentService;
    private static final Object API_KEY = "AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70";
    private final JsonParser jsonParser;

    @Autowired
    public MapController(StudentService budget2Service, JsonParser jsonParser) {
        this.studentService = budget2Service;
        this.jsonParser = jsonParser;
    }

//    @GetMapping("/getLocation")
//    public List getGeoDetails() {
//        //TODO: Make sure customer is added to the database and return the list
//        Response responseBody = studentService.getGeoDetails();
//        //return response.getBody();
//        return List.of(responseBody.getResult());
//    }


    @GetMapping("/getLocation")
    public Response getGeoDetails() {
        // Retrieve the list of location details from the service
        return studentService.getGeoDetails();
    }


//
//    @PostMapping("/register")
//    public ResponseEntity<String> addStudent(@RequestBody Student student) {
//        try {
//            studentRepository.save(student);
//            log.info("Student successfully added");
//            return ResponseEntity.ok("Student registered successfully.");
//        } catch (Exception e) {
//            log.error("Error adding student: {}", e.getMessage());
//            return ResponseEntity.status(500).body("Error registering student.");
//        }
//    }
}

