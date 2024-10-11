package com.example.project2.entity.repository;

import com.example.project2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
//    @Override
//    void deleteAll();
//    @Query("SELECT s FROM Student s WHERE s.password = ?1")
//    Optional<Student> findBudget2Byprice_level(int priceLevel);

    @Query("SELECT s FROM Student s WHERE s.userName = ?1 AND s.password = ?2")
    Optional<Student> findByUserNameAndPassword(String userName, String password);

    @Override
    void deleteAll();

}
