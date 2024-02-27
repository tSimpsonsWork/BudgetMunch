package com.example.project2.service;

import com.example.project2.entity.Employee;
import com.example.project2.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public ArrayList<Employee> getAll() {
        return employeeRepo.getAll();
    }
}
