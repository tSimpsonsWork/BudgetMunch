package com.example.project2.service;

import com.example.project2.entity.Student;
import com.example.project2.entity.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;


import java.util.*;

@Service
@Slf4j
@CrossOrigin("http://localhost:3000")
public class StudentService {
    //TODO: Add a goggle map search
    private final StudentRepository studentRepository;

//    private Student student;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
//        this.student = student;

    }

    public void saveStudent(List<Student> student) {
        studentRepository.saveAllAndFlush(student);
    }
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addStudent() throws Exception{
        HashSet<String> mapper = new HashSet<>();
        String gotu = "";
        try {
            //TODO: Students will get deleted before new so change if you want to keep
            List<Student> listOfStudents = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Student student = new Student();
                Random random = new Random();
                String randomName = "Student".concat(String.valueOf(random.nextLong(1, 1000)));
                student.setCustomerName(randomName);
                student.setUserName(randomName.concat(String.valueOf(i)));
                student.setEmail(randomName.concat("@fiu.edu"));
                student.setPassword(String.valueOf(random.nextInt(1,1000)));
                listOfStudents.add(student);
                mapper.add(student.getEmail());
            }
            studentRepository.saveAll(listOfStudents);
            log.info("Students successfully added");
        }catch (Exception e){
            log.error("Student unsuccessful added");
            throw new Exception();
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
