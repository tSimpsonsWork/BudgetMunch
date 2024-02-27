package com.example.project2.entity;

public class Employee {

    private int employeeId;
    private String name;
    private String address;

    public Employee(){
        super();
    }

    public Employee(int employeeId, String name, String address) {
        super();
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
