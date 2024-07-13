package com.example.project2;


import com.example.project2.entity.Student;
import com.example.project2.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.project2.Arely2.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Slf4j
public class Project2Application implements CommandLineRunner {

    private final StudentService studentService;

    @Autowired
    public Project2Application(StudentService studentService) {
        this.studentService = studentService;
    }
    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
        Result result = new Result();
        System.out.println(result);
    }
    @Override
    public void run(String... args) throws Exception {
        try {
            //TODO: Students will get deleted before new so change if you want to keep
            List<Student> listOfStudents = new ArrayList<>();
            studentService.deleteAllStudents();
            for (int i = 0; i < 20; i++) {
                Random random = new Random();
                String randomName = "Student".concat(String.valueOf(random.nextLong(1, 100)));
                Student student = new Student(random.nextLong(1, 100), randomName, randomName.concat("@fiu.edu"), random.nextInt(18, 60));
                listOfStudents.add(student);
            }
            studentService.saveStudent(listOfStudents);
            log.info("Students successfully added");
        }catch (Exception e){
            log.error("Student unsuccessful added");
            throw new Exception();
        }
    }
}