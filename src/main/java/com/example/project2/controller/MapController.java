package com.example.project2.controller;
import com.example.project2.entity.Result;
import com.example.project2.service.StudentService;
import com.example.project2.entity.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List getGeoDetails() throws JsonProcessingException {
        //TODO: Make sure customer is added to the database and return the list
        Response responseBody = studentService.getGeoDetails();
        responseBody.getResult();
        List<Result> list = List.of(responseBody.getResult());
        list.forEach(post->{
            if(post.getPrice_level()==0)
                post.setPrice_level(100);
            else if (post.getPrice_level()==1)
                post.setPrice_level(200);
            else if (post.getPrice_level()==2)
                post.setPrice_level(300);
            else
                post.setPrice_level(300);
        });
        //return response.getBody();
        return List.of(responseBody.getResult());

    }
}