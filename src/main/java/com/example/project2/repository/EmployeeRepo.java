package com.example.project2.repository;

import com.example.project2.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class EmployeeRepo {

    public ArrayList<Employee> employee = new ArrayList<Employee>();

    public ArrayList<Employee> getAll() {
        return employee;
    }
}
