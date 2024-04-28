package com.example.project2.BudgetMunchCode;

//import aj.org.objectweb.asm.TypeReference;


//import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Declaring inter-bean dependencies only work when declared within @Configuration class.
@Configuration
public class Budget2Config {
    @Bean //commandline runner is able to save our budget data within the repository
    CommandLineRunner commandLineRunner(Budget2Repository repository){
        Budget2 budget = new Budget2();
        return args -> {
           repository.save(budget);
        };
    }
}
