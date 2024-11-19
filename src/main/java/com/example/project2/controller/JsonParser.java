package com.example.project2.controller;
import com.example.project2.entity.User;
import com.example.project2.service.UserService;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

@Component
public class JsonParser {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    //initializing variables in json constructor
    public JsonParser(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public void parseAndSave(String json) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(json);// Parsing the JSON string into a JsonNode object
        JsonNode resultsNode = jsonNode.get("results");//get results node
        //converts the jsonNode to an Array of budget objects
        User[] budget2Array = objectMapper.convertValue(resultsNode, User[].class);
        List<User> budget2List = Arrays.asList(budget2Array);
        //saves the list of objects using the service method from service class
        userService.saveUser(budget2List); // Use instance method, not static method
    }
}
