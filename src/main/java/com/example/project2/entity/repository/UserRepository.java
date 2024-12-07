package com.example.project2.entity.repository;

import com.example.project2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
//    @Override
//    void deleteAll();
//    @Query("SELECT s FROM Student s WHERE s.password = ?1")
//    Optional<Student> findBudget2Byprice_level(int priceLevel);

    @Query("SELECT s FROM User s WHERE s.userName = ?1 AND s.password = ?2")
    Optional<User> findByUserNameAndPassword(String userName, String password);

    @Query("SELECT s FROM User s WHERE s.userName =?1")
    Optional<User> findByUserName(String userName);

    @Query("SELECT s FROM User s WHERE s.email =?1")
    Optional<User> findByEmail(String email);
    @Override
    void deleteAll();

}
