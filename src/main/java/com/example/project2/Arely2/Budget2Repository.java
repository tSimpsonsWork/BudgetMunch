package com.example.project2.Arely2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Budget2Repository extends JpaRepository<Budget2, Long> {

    @Query("SELECT b FROM Budget2 b WHERE b.email = ?1")
    Optional<Budget2> findBudget2ByEmail(String email);

}
