package com.example.project2.controller;

import com.example.project2.entity.Response;
import com.example.project2.entity.Result;
import com.example.project2.entity.Student;
import com.example.project2.entity.UserAddress;
import com.example.project2.entity.repository.StudentRepository;
import com.example.project2.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping(path = "api/v1/budget")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class MapController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @Autowired
    public MapController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }


    @PostMapping("/register")
    public ResponseEntity<?> newStudent(@RequestBody Student newStudent) {
        if (studentService.existsByUsername(newStudent.getUserName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        Student savedStudent = studentRepository.save(newStudent);
        return ResponseEntity.ok(savedStudent);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Student student) {
        Optional<Student> foundStudent = studentRepository.findByUserNameAndPassword(student.getUserName(), student.getPassword());
        if (foundStudent.isPresent()) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    @PostMapping("/address")
    public ResponseEntity<?> storeAddress(@RequestBody UserAddress userAddress) throws JsonProcessingException {
        String streetAddress = userAddress.getStreetAddress();
        String city = userAddress.getCity();
        String state = userAddress.getState();
        Double budget = userAddress.getBudget();
        // Double budget = userAddress.getBudget();
        //TODO:Add budget parameter for user to input their budget,

        if (streetAddress != null && !streetAddress.isEmpty() && city != null && !city.isEmpty() && state != null && !state.isEmpty()) {
            String message = String.format("%s, %s, %s", streetAddress, city, state);
            log.info(message);

            // Get GeoDetails from address
            ResponseEntity<String> geoDetailsResponse = studentService.getGeoDetails(message);
            if (geoDetailsResponse != null && geoDetailsResponse.getBody() != null) {
                // Use the geoDetailsResponse to fetch nearby restaurants
                Response nearbyRestaurants = studentService.getGeoDetails2(geoDetailsResponse);
                return ResponseEntity.ok(nearbyRestaurants);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to get geo details.");
            }
        } else {
            log.error("Invalid address data.");
            return ResponseEntity.badRequest().body("Invalid address data. Please provide street address, city, and state.");
        }
    }

    // Check if a username exists
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
        boolean exists = studentService.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }

    // Fetch nearby restaurants based on stored address (pass address as a parameter)


    @GetMapping("/getLocation")
    public ResponseEntity<List<Map<String, Object>>> getGeoLocation(@RequestParam String address, @RequestParam Double budget) throws JsonProcessingException {
        // Get the GeoDetails for the provided address
        ResponseEntity<String> geoDetailsResponse = studentService.getGeoDetails(address);
        if (geoDetailsResponse == null || geoDetailsResponse.getBody() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }

        // Fetch nearby restaurants
        Response nearbyRestaurants = studentService.getGeoDetails2(geoDetailsResponse);

        List<Result> resultList = List.of(nearbyRestaurants.getResult());
        List<Map<String, Object>> mappedResults = new ArrayList<>();

        resultList.forEach(post -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("name", post.getName());
            resultMap.put("rating", post.getRating());
            resultMap.put("vicinity", post.getVicinity());

            String priceLevelString;
            //variables to store the min and max values in
            double priceMin;
            double priceMax;

            // Handling the price levels
            switch (post.getPrice_level()) {
                case 0:
                    priceLevelString = "$1-$10";
                    priceMin = 1;
                    priceMax = 10;
                    break;
                case 1:
                    priceLevelString = "$10-$25";
                    priceMin = 10;
                    priceMax = 25;
                    break;
                case 2:
                    priceLevelString = "$25-$45";
                    priceMin = 25;
                    priceMax = 45;
                    break;

                default:
                    priceLevelString = "$50+";
                    priceMin = 50;
                    priceMax = Integer.MAX_VALUE;
                    break;
            }

            // LOGIC: If the person's budget is 25,
            //then they should cover the costs of all restaurants under $25
            if (budget > priceMin  || priceMin==1) {
                resultMap.put("price_level", priceLevelString);
                mappedResults.add(resultMap);}
        });

        // sorting the results by price range from the HashMap
        mappedResults.sort((a, b) -> {
            String priceLevelA = (String) a.get("price_level");
            String priceLevelB = (String) b.get("price_level");

            // Extract the min price from price levels
            int minPriceA = Integer.parseInt(priceLevelA.split("-")[0].replaceAll("[^\\d]", ""));
            int minPriceB = Integer.parseInt(priceLevelB.split("-")[0].replaceAll("[^\\d]", ""));

            return Integer.compare(minPriceA, minPriceB);
        });

        return ResponseEntity.ok(mappedResults);
    }
}
