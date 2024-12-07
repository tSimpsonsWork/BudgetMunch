package com.example.project2.controller;

import com.example.project2.entity.Response;
import com.example.project2.entity.Result;
import com.example.project2.entity.User;
import com.example.project2.entity.UserAddress;
import com.example.project2.entity.repository.UserRepository;
import com.example.project2.service.EmailService;
import com.example.project2.service.UserService;
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
@SessionAttributes("resetCodes") //Stores the reset codes temporarily
public class MapController {

    /*TODO:
       1) COMPLETE: Look at CodewithArjun video saved on YouTube to send an e-mail
       2)COMPLETE: Create a method that creates random 4 digit tokens and attach that token to the email
       3) COMPLETE ----but need to change the localhost pop-up error to be just a regular error--:the user must then input this token in the input box in order to change password
       4) After this, allow the users to change the password and update it in the database
       */

    private final UserService userService;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final Map<String, String> resetCodes = new HashMap<>();

    @Autowired
    public MapController(UserService userService, UserRepository userRepository, EmailService emailService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> newUser(@RequestBody User newUser) {
        List<String> errorMessages = new ArrayList<>();
        if (userService.existsByEmail(newUser.getEmail())) {
            errorMessages.add("Email already exists");
        }//verifies the user's email exists to prevent duplicated email
        if (userService.existsByUsername(newUser.getUserName())) {
            errorMessages.add("Username already exists");
        }//errors for existing duplicated email

        log.info(errorMessages.toString());

        if (!errorMessages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessages);
        }

        User savedUser = userRepository.save(newUser);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        log.error("Username: " + user.getUserName());
        log.error("Password: " + user.getPassword());
        Optional<User> foundUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (foundUser.isPresent()) {
            User loggedInUser = foundUser.get();
            Map<String, String> userDetails = new HashMap<>();
            userDetails.put("customerName", loggedInUser.getCustomerName());
            userDetails.put("username", loggedInUser.getUserName());
            userDetails.put("email", loggedInUser.getEmail());
            log.info("user details: {}", userDetails.get("customerName"));
            log.info("user details: {}", userDetails.get("username"));
            log.info("user details: {}", userDetails.get("email"));
            return ResponseEntity.ok(userDetails); // Return user details as JSON

        } else {
            log.error("Login failed :(");
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
            ResponseEntity<String> geoDetailsResponse = userService.getGeoDetails(message);
            if (geoDetailsResponse != null && geoDetailsResponse.getBody() != null) {
                // Use the geoDetailsResponse to fetch nearby restaurants
                Response nearbyRestaurants = userService.getGeoDetails2(geoDetailsResponse);
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
        boolean exists = userService.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable String email) {
        boolean exists = userService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    // Fetch nearby restaurants based on stored address (pass address as a parameter)


    @GetMapping("/getLocation")
    public ResponseEntity<List<Map<String, Object>>> getGeoLocation(@RequestParam String address, @RequestParam Double budget) throws JsonProcessingException {
        // Get the GeoDetails for the provided address
        ResponseEntity<String> geoDetailsResponse = userService.getGeoDetails(address);
        if (geoDetailsResponse == null || geoDetailsResponse.getBody() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }

        // Fetch nearby restaurants
        Response nearbyRestaurants = userService.getGeoDetails2(geoDetailsResponse);

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
            if (budget > priceMin || priceMin == 1) {
                resultMap.put("price_level", priceLevelString);
                mappedResults.add(resultMap);
            }
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

    //this generates a 4 digit code
    private String generateCode() {
        int code = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(code);
    }

    //Here I don't need to check for email because email was already checked in the front end
    @PostMapping("/send-email")
    public ResponseEntity<String> sendResetPassEmail(@RequestParam String to) {
        String code = generateCode();
        String subject = "Password Reset Code";
        String body = "Your password reset code is: " + code;
        //Store the code temporarily, such as in-memory storage or in the user Entity (must work on)
        resetCodes.put(to, code);//here we are mapping the resetCode to the user email
        emailService.sendEmail(to, subject, body);
        return ResponseEntity.ok("Email sent successfully!");
    }

    //Endpoint to verify code
    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String code) {
        String storedCode = resetCodes.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            resetCodes.remove(email); // Remove code once verified
            return ResponseEntity.ok("Code has been verified");
        } else {
            log.error("Invalid attempt with code: {} for email: {}", code, email); // Add logging here
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired code");
        }
    }

    @PostMapping("password-reset")
    public ResponseEntity<String> newPassword(@RequestParam String email, @RequestParam String newPassword){
        //Logic here to update the password of the user whose e-mail goes with the account
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return ResponseEntity.ok("Password has been reset successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }
    }

}




