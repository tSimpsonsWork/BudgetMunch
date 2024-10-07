package com.example.project2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class Response {
    @JsonProperty("results")//json annotation allows to map to results in json
    private Results[] results;

    //getter method retrieved the values of the result field
    public Results[] getResults() {
        return results;
    }

    //sets the value of result
    public void setResults(Results[] result) {
        this.results = results;
    }
}
