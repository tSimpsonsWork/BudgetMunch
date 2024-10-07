package com.example.project2.service;

import com.example.project2.entity.Location;
import com.example.project2.entity.Response;
import com.example.project2.entity.Results;
import com.example.project2.entity.Student;
import com.example.project2.entity.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@CrossOrigin("http://localhost:3000")
public class StudentService {
    //TODO: Add a goggle map search
    //TODO: Save a list of locations

    private final StudentRepository studentRepository;

//    private Student student;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
//        this.student = student;
    }

    public void saveStudent(List<Student> student) {
        studentRepository.saveAll(student);
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }


 // Add this import





//    public void addStudent2() {
//
//            ResponseEntity<Response> response = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/place/nearbysearch/json?&keyword=restaurant&location=25.7562465,-80.5279754&radius=10000&key=AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70"
//                    ,Response.class);
//
//            Response responseBody = response.getBody();
//            //TODO: This is where the restaurants go
//            if (responseBody != null && responseBody.getResult() != null) {
//                List<Result> results = List.of(responseBody.getResult());
//                for (Result result : results) {
//                    Student student = new Student();
//                    student.setCustomerName(result.getName());
//                    student.setUserName(result.getRating());
//                    student.setEmail(result.getVicinity());
//                    student.setPassword(result.getPrice_level());
//                    // Set other properties as needed
//                    //studentService.addStudent(student); // Assuming this method adds the budget2 object to the database
//                    studentRepository.save(student);
//                }
//            }
//    }

//    public Response getGeoDetails(){
//        //https://maps.googleapis.com/maps/api/place/textsearch/json?query=7785%20nw%2022%20court%20&key=AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70
////        ResponseEntity<Response> response = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/place/textsearch/json?query=7785%20NW%2022%20court%20&key=AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70"
////                ,Response.class);
//        ResponseEntity<Response> response = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/place/nearbysearch/json?&keyword=restaurant&location=25.7562465,-80.5279754&radius=10000&key=AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70"
//                ,Response.class);
//
//        return response.getBody();
//    }

    // This method retrieves geo details and returns a list of LocationDetails
    //private static final Object apiKey = "AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70";
    // Inside your StudentService class
    public Response getGeoDetails() {
        log.info("Starting to retrieve geo details...");

        // Make the API call
        ResponseEntity<Response> response = new RestTemplate().getForEntity(
                "https://maps.googleapis.com/maps/api/place/textsearch/json?query=590%20nw%20114%20ave%20&key=AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70",
                Response.class);

        log.info("Response status: {}", response.getStatusCode());
        log.info("Raw response body: {}", response.getBody());

        if (response.getStatusCode().is2xxSuccessful()) {
            Response responseBody = response.getBody();
            List<LocationDetails> locationDetailsList = new ArrayList<>();

            // Check if responseBody and results are not null
            if (responseBody != null && responseBody.getResults() != null) { // Make sure to check for 'getResults()'
                log.info("Response body retrieved successfully.");
                for (Results res : responseBody.getResults()) { // Loop through results
                    if (res.getGeometry() != null) {
                        Location location = res.getGeometry().getLocation(); // Get Location object
                        double latitude = location.getLat(); // Access latitude
                        double longitude = location.getLng(); // Access longitude
                        locationDetailsList.add(new LocationDetails(res.getName(), latitude, longitude)); // Create and add to list
                        log.info("Retrieved location: {}, Latitude: {}, Longitude: {}", res.getName(), latitude, longitude);
                    } else {
                        log.warn("Geometry is null for result: {}", res);
                    }
                }
            } else {
                log.warn("Response body or results are null.");
            }
            return response.getBody(); // Return the list of location details
        } else {
            log.error("Failed to retrieve geo details: {}", response.getStatusCode());
            throw new RuntimeException("Failed to retrieve geo details");
        }
    }

    // Inner class to hold location details
    public static class LocationDetails {
        private String name;
        private double latitude;
        private double longitude;

        public LocationDetails(String name, double latitude, double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }
}
