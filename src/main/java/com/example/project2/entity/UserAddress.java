package com.example.project2.entity;

import org.springframework.stereotype.Component;

@Component
public class UserAddress {
    //class holds the restaurants locations nearby
    private String streetAddress;
    private String city;
    private String state;
    private Double budget;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

}
