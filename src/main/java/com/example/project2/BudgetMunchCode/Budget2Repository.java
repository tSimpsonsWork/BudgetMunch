package com.example.project2.BudgetMunchCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository indicates that the class provides mechanism for:
////Storage retrieval, update, delete, and search
@Repository //responsible for data access
public interface Budget2Repository extends JpaRepository<Budget2, Long> {

    @Query("SELECT b FROM Budget2 b WHERE b.price_level = ?1")
    Optional<Budget2> findBudget2Byprice_level(int price_level);


}
