package com.example.project2;

import com.example.project2.entity.Student;
import com.example.project2.service.EmailService;
import com.example.project2.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class Project2Application implements CommandLineRunner{

    private final StudentService studentService;

    private Student student;

    @Autowired
    private EmailService emailService;

    @Autowired
    public Project2Application(StudentService studentService, Student student){
        this.studentService = studentService;
        this.student = student;
    }

    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
    }

    @Override
    //changed this
    public void run(String... args) throws Exception{
        //studentService.getGeoDetails();
        studentService.getStudents();
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail(){
//        emailService.sendEmail("arely.corre@hotmail.com",
//                "This is the subject",
//                "This is the Body of Email");
//    }
}
