package com.example.project2.Arely2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Budget2Service {
    private final Budget2Repository budget2Repository;

    @Autowired
    public Budget2Service(Budget2Repository budget2Repository){
        this.budget2Repository = budget2Repository;
    }

    public List<Budget2> getBudget2(){
               return budget2Repository.findAll();
    }

    public void addNewBudget2(Budget2 budget2) {

        Optional<Budget2> budget2Optional = budget2Repository
                .findBudget2ByEmail(budget2.getEmail());
        if(budget2Optional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        budget2Repository.save(budget2);
    }
}
