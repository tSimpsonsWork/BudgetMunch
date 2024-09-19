package com.example.project2.BudgetMunchCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Service has the business logic. It marks the class as a service provider
@Service
public class Budget2Service {
    private final Budget2Repository budget2Repository;

    @Autowired//constructor with autowiring od repository dependency
    public Budget2Service(Budget2Repository budget2Repository){
        this.budget2Repository = budget2Repository;
    }

    //saves all budget objects
    public void saveBudget2(List<Budget2> budget2){
        budget2Repository.saveAll(budget2);
    }

    //logic for retrieving budget data
    public List<Budget2> getBudget2(){
               return budget2Repository.findAll();
    }

    //logic for putting new data
    public void addNewBudget2(Budget2 budget2) {

        Optional<Budget2> budget2Optional = budget2Repository
                .findBudget2Byprice_level(budget2.getPrice_level());
        if(budget2Optional.isPresent()){
            throw new IllegalStateException("price_level taken");
        }
        budget2Repository.save(budget2);
    }

    //logic for deleting budget data
    public void deleteBudget2(Long budget2Id) {
        boolean exists = budget2Repository.existsById(budget2Id);
        if(!exists){
            throw new IllegalStateException("budget2 with id " + budget2Id + " does not exist");
        }
        budget2Repository.deleteById(budget2Id);

    }

    //logic for posting new budget information
    @Transactional
    public void updateBudget2(Long budget2Id, String name, int price_level) {
        Budget2 budget2 = budget2Repository.findById(budget2Id)
                .orElseThrow(() -> new IllegalStateException(
                        "budget2 with id " + budget2Id + "does not exist"
                ));
        //Objects part: if the name that you're trying to update
        // is not the same as the current one, then go ahead and set it
        if(name != null && name.length() > 0 && !Objects.equals(budget2.getName(), name)) {
            budget2.setName(name);
        }

        if( price_level > 0 && !Objects.equals(budget2.getPrice_level(), price_level)){
            Optional<Budget2> budget2Optional = budget2Repository
                    .findBudget2Byprice_level(price_level);
            if(budget2Optional.isPresent()){
                throw new IllegalStateException("price_level taken");
            }
            budget2.setPrice_level(price_level);

        }

    }
}
