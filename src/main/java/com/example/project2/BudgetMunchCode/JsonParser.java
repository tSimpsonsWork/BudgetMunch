package com.example.project2.BudgetMunchCode;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

@Component
public class JsonParser {

    private final Budget2Service budget2Service;
    private final ObjectMapper objectMapper;

    //initializing variables in json constructor
    public JsonParser(Budget2Service budget2Service, ObjectMapper objectMapper) {
        this.budget2Service = budget2Service;
        this.objectMapper = objectMapper;
    }

    public void parseAndSave(String json) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(json);// Parsing the JSON string into a JsonNode object
        JsonNode resultsNode = jsonNode.get("results");//get results node
        //converts the jsonNode to an Array of budget objects
        Budget2[] budget2Array = objectMapper.convertValue(resultsNode, Budget2[].class);
        List<Budget2> budget2List = Arrays.asList(budget2Array);
        //saves the list of objects using the service method from service class
        budget2Service.saveBudget2(budget2List); // Use instance method, not static method
    }
}