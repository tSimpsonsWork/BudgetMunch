package com.example.project2.service;

import com.example.project2.entity.Response; // Importing the Response entity to handle API response.
import com.example.project2.entity.Results; // Importing the Results entity to access lat/lng data.
import com.example.project2.entity.Student; // Importing the Student entity for student operations.
import com.example.project2.entity.repository.StudentRepository; // Importing the StudentRepository interface for database operations.
import com.fasterxml.jackson.databind.ObjectMapper; // Importing ObjectMapper for converting JSON to Java objects.
import lombok.extern.slf4j.Slf4j; // Importing Lombok for logging.
import org.springframework.beans.factory.annotation.Autowired; // Importing for dependency injection.
import org.springframework.http.ResponseEntity; // Importing ResponseEntity to handle HTTP responses.
import org.springframework.stereotype.Service; // Importing Service annotation to mark this class as a service.
import org.springframework.web.client.RestTemplate; // Importing RestTemplate for making HTTP requests.
import java.io.IOException; // Importing IOException for handling input-output exceptions.
import java.util.List; // Importing List for handling collections of students.

@Service // Indicates that this class is a Spring service.
@Slf4j // Enables logging for this class.
public class StudentService {

    private final StudentRepository studentRepository; // Declaring a final field for the student repository.

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

    // Method to retrieve geo details from an external API.
    public Response getGeoDetails() {
        log.info("Starting to retrieve geo details..."); // Logging the start of the geo details retrieval.

        // Make the API call and capture the response as a String.
        ResponseEntity<String> response = new RestTemplate().getForEntity(
                "https://maps.googleapis.com/maps/api/place/textsearch/json?query=590%20nw%20114%20ave%20&key=AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70",
                String.class); // The URL is hardcoded for querying a specific location.

        log.info("Response status: {}", response.getStatusCode()); // Logging the HTTP status code of the response.
        log.info("Raw response body: {}", response.getBody()); // Logging the raw JSON response received from the API.

        // Checking if the response status code indicates a successful request (2xx).
        if (response.getStatusCode().is2xxSuccessful()) {
            // Create an ObjectMapper instance to parse the JSON response.
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // Convert the raw JSON response to a Response object.
                Response responseBody = objectMapper.readValue(response.getBody(), Response.class);
                log.info("Parsed response body: {}", responseBody); // Logging the parsed response.

                // Check if responseBody and its results are not null.
                if (responseBody != null && responseBody.getResults() != null) {
                    log.info("Response body retrieved successfully."); // Log a success message.

                    // Log the latitude and longitude of each result
                    for (Results result : responseBody.getResults()) {
                        log.info("Latitude: {}, Longitude: {}", result.getGeometry().getLocation().getLat(), result.getGeometry().getLocation().getLng());
                    }
                    return responseBody; // Return the parsed Response object.
                } else {
                    log.warn("Response body or results are null."); // Log a warning if the response is null.
                    return null; // Return null, or consider throwing an exception as needed.
                }
            } catch (IOException e) {
                log.error("Error parsing JSON response: {}", e.getMessage()); // Log an error if parsing fails.
                return null; // Return null, or consider throwing an exception as needed.
            }
        } else {
            log.error("Failed to retrieve geo details: {}", response.getStatusCode()); // Log an error for a non-successful status.
            throw new RuntimeException("Failed to retrieve geo details"); // Throw a runtime exception for failed retrieval.
        }
    }
}
