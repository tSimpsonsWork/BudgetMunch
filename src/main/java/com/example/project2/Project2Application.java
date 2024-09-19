package com.example.project2;


import com.example.project2.entity.Response;
import com.example.project2.entity.Result;
import com.example.project2.entity.Student;
import com.example.project2.entity.repository.StudentRepository;
import com.example.project2.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@Slf4j
public class Project2Application implements CommandLineRunner{

    private final StudentService studentService;



    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);

    }

    @Override
    public void run(String... args){

        studentService.addStudent2();

//        try {
//            //TODO: Students will get deleted before new so change if you want to keep
//            List<Student> listOfStudents = new ArrayList<>();
//            studentService.deleteAllStudents();
//            for (int i = 0; i < 20; i++) {
//                Random random = new Random();
//                String randomName = "Student".concat(String.valueOf(random.nextLong(1, 100)));
//                Student student = new Student(1L,randomName, 1.1, "Hello", random.nextInt(18, 60));
//                listOfStudents.add(student);
//            }
//            studentService.saveStudent(listOfStudents);
//            log.info("Students successfully added");
//        }catch (Exception e){
//            log.error("Student unsuccessful added");
//            throw new Exception();
//        }
    }
}