package com.example.project2.Arely2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    public void deleteBudget2(Long budget2Id) {
        boolean exists = budget2Repository.existsById(budget2Id);
        if(!exists){
            throw new IllegalStateException("budget2 with id " + budget2Id + " does not exist");
        }
        budget2Repository.deleteById(budget2Id);

    }

    @Transactional
    public void updateBudget2(Long budget2Id, String name, String email) {
        Budget2 budget2 = budget2Repository.findById(budget2Id)
                .orElseThrow(() -> new IllegalStateException(
                        "budget2 with id " + budget2Id + "does not exist"
                ));
        //Objects part: if the name that you're trying to update
        // is not the same as the current one, then go ahead and set it
        if(name != null && name.length() > 0 && !Objects.equals(budget2.getName(), name)) {
            budget2.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(budget2.getEmail(), email)){
            Optional<Budget2> budget2Optional = budget2Repository
                    .findBudget2ByEmail(email);
            if(budget2Optional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            budget2.setEmail(email);

        }

    }
}
