package com.example.project2.BudgetMunchCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
//@RestController simplifies the creation of RESTful web services
//Allows the class to handle requests made by the client.
//It helps us handle REST APIs such as GET, POST, Delete, and Put requests
@RestController
@RequestMapping(path="api/v1/student")
public class Budget2Controller {

    private final Budget2Service budget2Service;
    private static final Object API_KEY = "AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70";

    private final JsonParser jsonParser;

    @PostMapping("/import")
    public void importData(@RequestBody String json) throws Exception {
        jsonParser.parseAndSave(json);
    }

    @Autowired //instantiates service class and the jsonparser for our api
    public Budget2Controller (Budget2Service budget2Service, JsonParser jsonParser){
        this.budget2Service = budget2Service;
        this.jsonParser = jsonParser;
    }
    @GetMapping //Get Request
    public List<Budget2> getBudget2(){
        return budget2Service.getBudget2();
    }
    @PostMapping //Post Request
    public void registerNewBudget2(@RequestBody Budget2 budget2){
        budget2Service.addNewBudget2(budget2);
    }

    @DeleteMapping(path="{budget2Id}") //Delete Request
    public void deleteBudget2(@PathVariable("budget2Id") Long budget2Id){
        budget2Service.deleteBudget2(budget2Id);
    }

    @PutMapping(path="{budget2Id}") //Put request
    public void updateBudget2(
            @PathVariable("budget2Id") Long budget2Id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int price_level){
        budget2Service.updateBudget2(budget2Id, name, price_level);
    }

    //gets information from the api using FIU's coordinates
    @GetMapping("/getLocation")
    public Response getGeoDetails(){
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

        return response.getBody();
    }

    //information gathered from google maps api
    @PostMapping("/importFromApi")
    public void importDataFromApi(){
        String apiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=restaurant&location=25.7562465,-80.5279754&radius=10000&key=" + API_KEY;

        ResponseEntity<Response> response = new RestTemplate().getForEntity(apiUrl, Response.class);
        Response responseBody = response.getBody();

        if (responseBody != null && responseBody.getResult() != null) {
            List<Result> results = List.of(responseBody.getResult());
            for (Result result : results) {
                Budget2 budget2 = new Budget2();
                budget2.setName(result.getName());
                budget2.setPrice_level(result.getPrice_level());
                // Set other properties as needed
                budget2Service.addNewBudget2(budget2); // Assuming this method adds the budget2 object to the database
            }
        }
    }
}
