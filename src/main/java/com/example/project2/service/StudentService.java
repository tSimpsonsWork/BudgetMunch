package com.example.project2.service;

import com.example.project2.entity.Response;
import com.example.project2.entity.Student;
import com.example.project2.entity.Result;
import com.example.project2.entity.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service // Indicates that this class is a Spring service.
@Slf4j // Enables logging for this class.
public class StudentService {
    private final StudentRepository studentRepository;
    private Result result;

    @Value("${api.key}")
    private String apiKey;

    // Constructor for dependency injection of the StudentRepository.
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository; // Initializing the studentRepository field.
    }

    // Method to save a list of students to the database.
    public void saveStudent(List<Student> students) {
        studentRepository.saveAll(students); // Saving all student records to the repository.
    }

    // Method to delete all students from the database.
    public void deleteAllStudents() {
        studentRepository.deleteAll(); // Deleting all student records from the repository.
    }

    // Method to retrieve all students from the database.
    public List<Student> getStudents() {
        return studentRepository.findAll(); // Returning the list of all students from the repository.
    }

    public boolean existsByUsername(String username){
        return studentRepository.findByUserName(username).isPresent();
    }

    public boolean existsByEmail(String email){
        return studentRepository.findByEmail(email).isPresent();
    }
    public ResponseEntity<String> getGeoDetails(String message) throws JsonProcessingException {
        //https://maps.googleapis.com/maps/api/place/textsearch/json?query=7785%20nw%2022%20court%20&key=AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70
        ResponseEntity<String> response = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/geocode/json?address=" + message.replace(" ","+") + "&key="+apiKey
                , String.class);
        //log.info(String.valueOf(response));
        return response;
    }

    public Response getGeoDetails2(ResponseEntity<String> geoDetails) throws JsonProcessingException{

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(geoDetails.getBody());// Parsing the JSON string into a JsonNode object

        // Extract the address_components array
        JsonNode addressComponents = jsonNode.findPath("address_components");

        boolean streetNumberExists = false;
        boolean routeExists = false;

        // Loop to check for street_number and route
        for (JsonNode component : addressComponents) {
            JsonNode types = component.path("types");
            for (JsonNode type : types) {
                if (type.asText().equals("street_number")) {
                    streetNumberExists = true;
                } else if (type.asText().equals("route")) {
                    routeExists = true;
                }
            }
            // Exit early if both are found
            if (streetNumberExists && routeExists) break;
        }

        // Output result
        log.info("this is the street address --------> {}",streetNumberExists);
        log.info("this is the route --------> {}",routeExists);
        if(streetNumberExists  &&routeExists) {
            JsonNode latNode = jsonNode.findPath("location").get("lat");
            JsonNode lngNode = jsonNode.findPath("location").get("lng");//get results node

            log.info("this is the lat --------> {}", latNode);
            log.info("this is the lng --------> {}", lngNode);
            String lat = latNode.toPrettyString();
            String lng = lngNode.toPrettyString();
            ResponseEntity<Response> userResponse = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/place/nearbysearch/json?&keyword=restaurant&location=" + lat + ',' + lng + "&radius=1000&key="+apiKey
                    , Response.class);
            return userResponse.getBody();
        }else{
            ResponseEntity<Response> userResponse = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/place/nearbysearch/json?&keyword=restaurant&location=\"\"&radius=1000&key="+apiKey
                    , Response.class);
            return userResponse.getBody();
        }



    }
    //TODO: 1st getGeoDetails will handle the 1st url in which you will pass in the address as a parameter, which will return a Response that gets the lat and lon
    //TODO: 2nd getGeoDetails will then get the lattitude and longitude from the 1st geoDetails, which will


}
