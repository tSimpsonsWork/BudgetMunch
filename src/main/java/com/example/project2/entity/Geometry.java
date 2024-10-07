package com.example.project2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geometry {
    @JsonProperty("location")
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
