package com.example.project2.entity;

import org.springframework.stereotype.Component;

@Component
public class Result {

    //all the variables stored in the result of the Json
    private String name;
    private int price_level;
    private double rating;
    private String vicinity;

    //all the getters and setters for the variables that show up on the json

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice_level() {
        return price_level;
    }

    public void setPrice_level(int price_level) {
        this.price_level = price_level;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}