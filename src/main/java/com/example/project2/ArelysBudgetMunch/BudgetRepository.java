//package com.example.project2.ArelysBudgetMunch;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
////@Repository indicates that the class provides mechanism for:
////Storage retrieval, update, delete, and search
//@Repository
//public interface BudgetRepository extends JpaRepository<Budget, Long> {
//
//    @Query("SELECT b FROM Budget b WHERE b.maxPrice = ?1")
//    Optional<Budget> findRestaurantByBudgetPrice(Double maxPrice);
//
//}
