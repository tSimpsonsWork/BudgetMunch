package com.example.project2;
import com.example.project2.entity.Response;
import com.example.project2.entity.Student;
import com.example.project2.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Project2Application implements CommandLineRunner{

    private final StudentService studentService;

    private Student student;

    @Autowired
    public Project2Application(StudentService studentService, Student student){
        this.studentService = studentService;
        this.student = student;
    }

    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception{
        studentService.getGeoDetails();
        //Response responseBody = studentService.getGeoDetails();
        //ArrayList<Result[]> list = new ArrayList<>();
        //list.add(responseBody.getResult());
        studentService.addStudent();
    }
}