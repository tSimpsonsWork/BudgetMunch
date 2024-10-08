package com.example.project2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Location {
    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lng")
    private double lng;

    public Location(String name, double latitude, double longitude) {
    }

    public double getLat() {
        return lat;
    }

    @JsonProperty("location")
    public void setLatLng(Map<String,String>location) {
        this.lat = (Float.parseFloat(location.get("lat")));
        this.lng = (Float.parseFloat(location.get("lng")));
    }

    public double getLng() {
        return lng;
    }

    @JsonProperty("location")
    public void setLng(Map<String,String> location) {
        this.lng = (Float.parseFloat(location.get("lng")));
    }
}
