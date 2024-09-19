package com.example.project2.controller;
import com.example.project2.entity.Student;
import com.example.project2.service.StudentService;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

@Component
public class JsonParser {

    private final StudentService studentService;
    private final ObjectMapper objectMapper;

    //initializing variables in json constructor
    public JsonParser(StudentService studentService, ObjectMapper objectMapper) {
        this.studentService = studentService;
        this.objectMapper = objectMapper;
    }

    public void parseAndSave(String json) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(json);// Parsing the JSON string into a JsonNode object
        JsonNode resultsNode = jsonNode.get("results");//get results node
        //converts the jsonNode to an Array of budget objects
        Student[] budget2Array = objectMapper.convertValue(resultsNode, Student[].class);
        List<Student> budget2List = Arrays.asList(budget2Array);
        //saves the list of objects using the service method from service class
        studentService.saveStudent(budget2List); // Use instance method, not static method
    }
}