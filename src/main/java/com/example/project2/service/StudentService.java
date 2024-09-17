package com.example.project2.service;

import com.example.project2.entity.Response;
import com.example.project2.entity.Result;
import com.example.project2.entity.Student;
import com.example.project2.entity.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
@CrossOrigin("http://localhost:3000")
public class StudentService {
    //TODO: Add a goggle map search
    //TODO: Save a list of locations

    private final StudentRepository studentRepository;

    @Autowired
    private Student student;

    @Autowired
    public StudentService(StudentRepository studentRepository, Student student) {
        this.studentRepository = studentRepository;
        this.student = student;
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

    public void addStudent(Student student) {

        Optional<Student> budget2Optional = studentRepository
                .findBudget2Byprice_level(student.getPriceLevel());
        if(budget2Optional.isPresent()){
            throw new IllegalStateException("price_level taken");
        }
        studentRepository.save(student);
    }

    public void addStudent2() {

            ResponseEntity<Response> response = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/place/nearbysearch/json?&keyword=restaurant&location=25.7562465,-80.5279754&radius=10000&key=AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70"
                    ,Response.class);

            Response responseBody = response.getBody();
            //TODO: This is where the restaurants go
            if (responseBody != null && responseBody.getResult() != null) {
                List<Result> results = List.of(responseBody.getResult());
                for (Result result : results) {
                    Student student = new Student();
                    student.setName(result.getName());
                    student.setRating(result.getRating());
                    student.setVicinity(result.getVicinity());
                    student.setPriceLevel(result.getPrice_level());
                    // Set other properties as needed
                    //studentService.addStudent(student); // Assuming this method adds the budget2 object to the database
                    studentRepository.save(student);
                }
            }
    }

    @Transactional
    public void updateStudent(Long budget2Id, String name, int price_level) {
        Student student = studentRepository.findById(budget2Id)
                .orElseThrow(() -> new IllegalStateException(
                        "budget2 with id " + budget2Id + "does not exist"
                ));
        //Objects part: if the name that you're trying to update
        // is not the same as the current one, then go ahead and set it
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (price_level > 0 && !Objects.equals(student.getPriceLevel(), price_level)) {
            Optional<Student> budget2Optional = studentRepository
                    .findBudget2Byprice_level(price_level);
            if (budget2Optional.isPresent()) {
                throw new IllegalStateException("price_level taken");
            }
            student.setPriceLevel(price_level);

        }

    }

    public Response getGeoDetails(){
        ResponseEntity<Response> response = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/place/nearbysearch/json?&keyword=restaurant&location=25.7562465,-80.5279754&radius=10000&key=AIzaSyAaheJOXHcdlFq7UWAe7vuumLPeNdUaW70"
                ,Response.class);

        return response.getBody();
    }
}
