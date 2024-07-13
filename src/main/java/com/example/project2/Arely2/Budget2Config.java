package com.example.project2.Arely2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;
import static java.util.Calendar.MAY;

@Configuration
public class Budget2Config {
    @Bean
    CommandLineRunner commandLineRunner(Budget2Repository repository){
        return args -> {
            Budget2 arely = new Budget2(
                    "Arely",
                    "arely@gmail.com",
                    LocalDate.of(2003, JANUARY, 1)

            );
            Budget2 amy = new Budget2(
                    "Amy",
                    "amy@gmail.com",
                    LocalDate.of(2002, MAY, 29)

            );
            repository.saveAll(
                    List.of(arely,amy)
            );
        };
    }
}
