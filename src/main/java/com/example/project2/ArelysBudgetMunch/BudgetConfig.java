//package com.example.project2.ArelysBudgetMunch;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//// Declaring inter-bean dependencies only work when
////declared within @Configuration class.
////I think this is to run at start-up
//@Configuration
//public class BudgetConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(
//            BudgetRepository repository){
//        return args -> {
//            Budget sixDollars = new Budget(
//                    6.0,
//                    "Miami",
//                    "Chik-fil-A",
//                    "Chicken Sandwich",
//                    5.75
//            );
//            Budget fourDollars = new Budget(
//                    4.0,
//                    "Miami",
//                    "Hooters",
//                    "Fire Wings",
//                    3.50
//            );
//            //hibernate works when we invoke saveAll
//            repository.saveAll(
//                    List.of(sixDollars,fourDollars)
//            );
//        };
//    }
//}
