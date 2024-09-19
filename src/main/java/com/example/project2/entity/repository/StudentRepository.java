package com.example.project2.entity.repository;

import com.example.project2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Override
    void deleteAll();

    @Query("SELECT s FROM Student s WHERE s.priceLevel = ?1")
    Optional<Student> findBudget2Byprice_level(int priceLevel);

}
