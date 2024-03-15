package com.example.project2.Arely2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class Budget2Controller {

    private final Budget2Service budget2Service;

    @Autowired
    public Budget2Controller (Budget2Service budget2Service){
        this.budget2Service = budget2Service;
    }
    @GetMapping
    public List<Budget2> getBudget2(){
        return budget2Service.getBudget2();
    }
    @PostMapping
    public void registerNewBudget2(@RequestBody Budget2 budget2){
        budget2Service.addNewBudget2(budget2);
    }

}
