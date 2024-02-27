package com.example.project2;

import com.example.project2.entity.Employee;
import com.example.project2.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Project2Application implements CommandLineRunner {

    @Autowired
    EmployeeRepo employeeRepo;

    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
    }
//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return String.format("Hello %s!", name);
//    }

    @Override
    public void run(String... args) throws Exception {
        Employee e1 = new Employee(1,"Luffy","Grandline");
        Employee e2 = new Employee(2,"Doflamingo", "Dressrosa");

        employeeRepo.employee.addAll(Arrays.asList(e1,e2));
    }

//Feature with TS
}
