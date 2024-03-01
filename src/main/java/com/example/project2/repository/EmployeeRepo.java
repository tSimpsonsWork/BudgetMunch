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

    public String add(Employee emp) {
        employee.add(emp);
        return "Successful Added!!!";
    }

    public String edit(Employee emp) {
        employee.stream().filter(e -> e.getEmployeeId() == emp.getEmployeeId()).forEach(e ->{
            e.setAddress(emp.getAddress());
            e.setName(emp.getName());
        });

        return "Successful Updated!!!";
    }

    public String delete(int empNo) {
        employee.remove(empNo-1);
        return "Deleted!";
    }
}
