package com.example.project2;

import com.example.project2.entity.Employee;
import com.example.project2.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Project2Application implements CommandLineRunner {

    @Autowired
    EmployeeRepo employeeRepo;

    @Value("${url}")
    private String value;

    @Value("${nums}")
    private int[] nums;

    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(value);

        for(int a:nums)
            System.out.println(a);


        Employee e1 = new Employee(1,"Luffy","Grandline");
        Employee e2 = new Employee(2,"Doflamingo", "Dressrosa");

        employeeRepo.employee.addAll(Arrays.asList(e1,e2));
    }

//Feature with TS
}
