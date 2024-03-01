package com.example.project2.controller;

import com.example.project2.entity.Employee;
import com.example.project2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/all")
    public ArrayList<Employee> getAll(){
        return employeeService.getAll();
    }

    @PostMapping("/add")
    public String add(@RequestBody Employee employee){
        return employeeService.add(employee);
    }

    @PutMapping("/edit")
    public String edit(@RequestBody Employee employee){
        return employeeService.edit(employee);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam int empNo){
        return employeeService.delete(empNo);
    }

        @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/goodbye/{name}")//grabs the {name}
    public String goodbye(@PathVariable String name) {
        return String.format("Goodbye %s!", name);
    }

    
}
