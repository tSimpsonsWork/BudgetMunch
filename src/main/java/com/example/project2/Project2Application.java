package com.example.project2;

import com.example.project2.entity.User;
import com.example.project2.service.EmailService;
import com.example.project2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class Project2Application implements CommandLineRunner{

    private final UserService userService;

    private User user;

    @Autowired
    private EmailService emailService;

    @Autowired
    public Project2Application(UserService userService, User user){
        this.userService = userService;
        this.user = user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
    }

    @Override
    //changed this
    public void run(String... args) throws Exception{
        //studentService.getGeoDetails();
        userService.getUsers();
    }

}
