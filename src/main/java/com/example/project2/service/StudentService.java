package com.example.project2.service;

import com.example.project2.entity.Response;
import com.example.project2.entity.Student;
import com.example.project2.entity.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

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

    @CrossOrigin("http://localhost:3000") // Allow CORS for frontend
        // Other methods...

        @PostMapping("/register")
        public ResponseEntity<String> addStudent(@RequestBody Student student) {
            try {
                studentRepository.save(student);
                log.info("Student successfully added");
                return ResponseEntity.ok("Student registered successfully.");
            } catch (Exception e) {
                log.error("Error adding student: {}", e.getMessage());
                return ResponseEntity.status(500).body("Error registering student.");
            }
        }



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



    public Response getGeoDetails(){
        ResponseEntity<Response> response = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/place/nearbysearch/json?&keyword=restaurant&location=25.7562465,-80.5279754&radius=10000&key=AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70"
                ,Response.class);

        return response.getBody();
    }
}
