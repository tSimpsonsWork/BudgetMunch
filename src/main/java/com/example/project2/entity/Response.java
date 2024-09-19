package com.example.project2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class Response {
    @JsonProperty("results")//json annotation allows to map to results in json
    private Result[] result;

    //getter method retrieved the values of the result field
    public Result[] getResult() {
        return result;
    }

    //sets the value of result
    public void setResult(Result[] result) {
        this.result = result;
    }
}