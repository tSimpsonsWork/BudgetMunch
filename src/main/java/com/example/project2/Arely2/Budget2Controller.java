package com.example.project2.Arely2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class Budget2Controller {

    private final Budget2Service budget2Service;
    private static final Object API_KEY = "";

    @Autowired
    public Budget2Controller (Budget2Service budget2Service){
        this.budget2Service = budget2Service;
    }
    @GetMapping
    public List<Budget2> getBudget2(){
        return budget2Service.getBudget2();
    }
    @PostMapping
    public void registerNewBudget2(@RequestBody Budget2 budget2){
        budget2Service.addNewBudget2(budget2);
    }

    @DeleteMapping(path="{budget2Id}")
    public void deleteBudget2(@PathVariable("budget2Id") Long budget2Id){
        budget2Service.deleteBudget2(budget2Id);
    }

    @PutMapping(path="{budget2Id}")
    public void updateBudget2(
            @PathVariable("budget2Id") Long budget2Id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        budget2Service.updateBudget2(budget2Id, name, email);
    }
//    @GetMapping("/getLocation")
//    public Response getGeoDetails(@RequestParam String address){
//        UriComponents uri = UriComponentsBuilder.newInstance()
//                .scheme("https")
//                .host("maps.googleapis.com")
//                .path("/maps/api/place/nearbysearch/json")
//                .queryParam("key", API_KEY)
//                .queryParam("address", address)
//                .build();
//         System.out.println(uri.toUriString());
//         ResponseEntity<Response> response = new RestTemplate().getForEntity(uri.toUriString()
//        ,Response.class);
//        return response.getBody();
//    }

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
         ResponseEntity<Response> response = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/place/nearbysearch/json?&keyword=restaurant&location=25.7562465,-80.5279754&radius=10000&key="
        ,Response.class);
        return response.getBody();
    }
}
