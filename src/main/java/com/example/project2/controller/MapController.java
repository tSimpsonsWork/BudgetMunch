package com.example.project2.controller;
//
//import com.example.project2.entity.Student;
//import com.example.project2.entity.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//import java.util.List;
//
//@RestController
//@CrossOrigin("http://localhost:3000")
//public class MapController {
//
//
//
//    @Autowired
//    StudentRepository studentRepository;
//
//
//    public String getBook(@PathVariable int id) {
//        return "Hello World";
//    }
//
//    @GetMapping("/info")
//    List<Student> getStudents(){
//        return studentRepository.findAll();
//    }
//}


import com.example.project2.entity.Result;
import com.example.project2.entity.Student;
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

    @PostMapping("/import")
    public void importData(@RequestBody String json) throws Exception {
        jsonParser.parseAndSave(json);
    }

    @Autowired //instantiates service class and the jsonparser for our api
    public MapController(StudentService budget2Service, JsonParser jsonParser){
        this.studentService = budget2Service;
        this.jsonParser = jsonParser;
    }
    @GetMapping //Get Request
    public List<Student> getStudent(){
        return studentService.getStudent ();
    }
//    @PostMapping //Post Request
//    public void registerNewBudget2(@RequestBody Student budget2){
//        studentService.addNewBudget2(budget2);
//    }

//    @DeleteMapping(path="{budget2Id}") //Delete Request
//    public void deleteBudget2(@PathVariable("budget2Id") Long budget2Id){
//        studentService.deleteBudget2(budget2Id);
//    }

    @PutMapping(path="{budget2Id}") //Put request
    public void updateBudget2(
            @PathVariable("budget2Id") Long budget2Id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int price_level){
        studentService.updateStudent(budget2Id, name, price_level);
    }

    //gets information from the api using FIU's coordinates
    @GetMapping("/getLocation")
    public List getGeoDetails(){
//        UriComponents uri = UriComponentsBuilder.newInstance()
//                .scheme("https")
//                .host("maps.googleapis.com")
//                .path("/maps/api/place/nearbysearch/json")
//                .queryParam("key", API_KEY)
//                .queryParam("address", address)
//                .build();
//         System.out.println(uri.toUriString());
        ResponseEntity<Response> response = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/place/nearbysearch/json?&keyword=restaurant&location=25.7562465,-80.5279754&radius=10000&key=AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70"
                ,Response.class);
        Response responseBody = response.getBody();
        //return response.getBody();
        return List.of(responseBody.getResult());

    }

    //information gathered from google maps api
    @PostMapping("/importFromApi")
    public void importDataFromApi(){
        String apiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=restaurant&location=25.7562465,-80.5279754&radius=10000&key=" + API_KEY;

        ResponseEntity<Response> response = new RestTemplate().getForEntity(apiUrl, Response.class);
        Response responseBody = response.getBody();
        //TODO: This is where the restaurants go
        if (responseBody != null && responseBody.getResult() != null) {
            List<Result> results = List.of(responseBody.getResult());
            for (Result result : results) {
                Student student = new Student();
                student.setName(result.getName());
                student.setRating(result.getRating());
                student.setVicinity(result.getVicinity());
                student.setPriceLevel(result.getPrice_level());
                // Set other properties as needed
                studentService.addStudent(student); // Assuming this method adds the budget2 object to the database
            }
        }
    }
}