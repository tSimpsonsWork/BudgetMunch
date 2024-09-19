package com.example.project2.controller;
import com.example.project2.service.StudentService;
import com.example.project2.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
//@RestController simplifies the creation of RESTful web services
//Allows the class to handle requests made by the client.
//It helps us handle REST APIs such as GET, POST, Delete, and Put requests
@RestController
@RequestMapping(path="api/v1/budget")
@CrossOrigin("http://localhost:3000")
public class MapController {

    private final StudentService studentService;
    private static final Object API_KEY = "AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70";
    private final JsonParser jsonParser;

    @Autowired
    public MapController(StudentService budget2Service, JsonParser jsonParser){
        this.studentService = budget2Service;
        this.jsonParser = jsonParser;
    }

    @GetMapping("/getLocation")
    public List getGeoDetails(){
        //TODO: Make sure customer is added to the database and return the list
        Response responseBody = studentService.getGeoDetails();
        //return response.getBody();
        return List.of(responseBody.getResult());

    }
}