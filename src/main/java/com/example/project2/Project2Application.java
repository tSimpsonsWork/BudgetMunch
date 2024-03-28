package com.example.project2;

import com.example.project2.Arely2.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Project2Application {

    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
        Result result = new Result();
        System.out.println(result);
    }
//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return String.format("Hello %s!", name);
//    }




}
